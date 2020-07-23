import { Component, OnInit, ViewChild, OnDestroy, Inject } from "@angular/core";
import { Router } from '@angular/router';
import { Cluster } from '../modals/Cluster';
import { ClusterService } from '../cluster.service';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Table } from '../modals/Table';
import { SelectionModel } from '@angular/cdk/collections';
import { MatPaginator, MatSort, MatTableDataSource } from "@angular/material";

@Component({
  selector: 'app-clusters-view',
  templateUrl: './clusters-view.component.html',
  styleUrls: ['./clusters-view.component.scss']
})

export class ClustersViewComponent implements OnInit {

  public clusterList: Cluster[];
  public clusterdata: Cluster[];
  public clusterfilter: string;

  dataSource = new MatTableDataSource<Cluster>(this.clusterdata);
  selection = new SelectionModel<Cluster>(true, []);


  @ViewChild(MatPaginator, { static: false }) paginator: MatPaginator;

  constructor(private router: Router, private clusterService: ClusterService, private dialog: MatDialog) { }

  ngOnInit() {
    this.getClusters();
    console.log("Cluster data is " + this.clusterdata);
  }


  displayedColumns: string[] = ['select', 'cluster_name', 'cluster_description', 'cluster_date', 'update', 'delete']

  exportTable() {
    console.log(this.dataSource);
    // this.displayedColumns=this.displayedColumns.slice(1,4);
    Table.exportToExcel("cluster","Cluster");
  }

  public doFilter = (value: string) => {
    this.dataSource.filter = value.trim().toLowerCase();
  }


editDialog(cluster: Cluster) {
  console.log(cluster);
  const dialogRef = this.dialog.open(ClusterEditDialog,
    {
      width: '300px',
      height: 'auto',
      data: { cluster }
    });
  dialogRef.afterClosed().subscribe(result => {
    console.log(result);
    if (result != undefined) {
      this.editCluster(result);
    }
    else {
      this.getClusters();
    }
  });
}

createCluster() {
  this.router.navigateByUrl('cluster');
}

getClusters() {
  this.clusterService.getAllClusters().subscribe(data => {
    this.clusterList = data;
    // .filter(x => x.isdeleted != true);
    this.clusterdata = Object.assign(this.clusterList);
    this.dataSource = new MatTableDataSource<Cluster>(this.clusterdata);
    this.selection = new SelectionModel<Cluster>(true, []);
    this.dataSource.paginator = this.paginator;
  })
}

editCluster(cluster: Cluster) {
  console.log(cluster);
  this.clusterService.editCluster(cluster).subscribe(
    (data) => {
      console.log(data)
      this.getClusters();
    })
}

deleteCluster(cluster: Cluster)
 {
  cluster.isdeleted = true;
  this.clusterService.editCluster(cluster).subscribe(
    (data) => {
      console.log(data)
      this.getClusters();
    })
}

isAllSelected() {
  const numSelected = this.selection.selected.length;
  const numRows = this.dataSource.data.length;
  return numSelected === numRows;
}

removeSelectedRows() {
  this.selection.selected.forEach(item => {
    // item.cluster_id;
    this.deleteCluster(this.clusterdata.find(x => x === item));
    // console.log("Cluster fiound"+this.clusterdata.find(x => x === item).cluster_id);
    // this.clusterdata.splice(index, 1)
    // this.dataSource = new MatTableDataSource<Cluster>(this.clusterdata);
  });
  this.selection = new SelectionModel<Cluster>(true, []);
}

/** Selects all rows if they are not all selected; otherwise clear selection. */
masterToggle() {
  this.isAllSelected() ?
    this.selection.clear() :
    this.dataSource.data.forEach(row => this.selection.select(row));
}

}

@Component({
  selector: 'cluster-edit-dialogue',
  templateUrl: 'ClusterEditDialog.html',
})

export class ClusterEditDialog {

  constructor(

    public dialogRef: MatDialogRef<ClusterEditDialog>,
    @Inject(MAT_DIALOG_DATA) public data: Cluster) {
    console.log(data);
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

}
