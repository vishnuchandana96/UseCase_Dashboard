import { Component, OnInit,Inject , ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { ResourceService } from '../resource.service';
import { Resource } from '../modals/Resource';
import {Table} from '../modals/Table';
import { SelectionModel } from '@angular/cdk/collections';
import { MatTableDataSource , MatPaginator} from '@angular/material';

@Component({
  selector: 'app-resource-view',
  templateUrl: './resource-view.component.html',
  styleUrls: ['./resource-view.component.scss']
})
export class ResourceViewComponent implements OnInit {

  public resourceList : Resource[]
  public resourcedata : Resource[]

  dataSource = new MatTableDataSource<Resource>(this.resourcedata);
  selection = new SelectionModel<Resource>(true, []);

  @ViewChild(MatPaginator, {static : false}) paginator: MatPaginator;

  constructor(private router : Router , private resourceService : ResourceService , private dialog : MatDialog) { }

  ngOnInit() {
    this.getResources();
  }

  displayedColumns : string[] = ['select','cluster_name','application_name','emp_id','emp_name','emp_description','update','delete']

  exportTable(){
    Table.exportToExcel("resource");
  }

  public doFilter = (value: string) => {
    this.dataSource.filter = value.trim().toLowerCase();
  }
  
  createResource()
  {
      this.router.navigateByUrl('resource');
  }

  getResources()
  {
    this.resourceService.getAllResources().subscribe(data => {
      this.resourceList = data;
      this.resourcedata = Object.assign(this.resourceList);
      this.dataSource = new MatTableDataSource<Resource>(this.resourcedata);
      this.selection = new SelectionModel<Resource>(true, []);
      this.dataSource.paginator = this.paginator;
    })
  
  }

  editDialog(resource : Resource) {
  console.log(resource);
    const dialogRef = this.dialog.open(ResourceEditDialog,
      {
        width: '300px',
        height: 'auto',
        data: {resource}
      });
    dialogRef.afterClosed().subscribe(result => {
      if (result != undefined) {
        this.editResource(result);
      }
      else
      {
        this.getResources();
      }
    });
  }

  editResource(resource : Resource)
  {
    console.log(resource);
    this.resourceService.editResource(resource).subscribe(
      (data) => {
        console.log(data)
        this.getResources();
      })
  }

  deleteResource(resource : Resource)
  {
    resource.isdeleted = true;
    this.resourceService.editResource(resource).subscribe(
      (data) => {
        console.log(data)
        this.getResources();
      }) 
  }

  isAllSelected() {
    const numSelected = this.selection.selected.length;
    const numRows = this.dataSource.data.length;
    return numSelected === numRows;
  }

  removeSelectedRows() {
    this.selection.selected.forEach(item => {
      this.deleteResource(this.resourcedata.find(x => x === item));
    });
    this.selection = new SelectionModel<Resource>(true, []);
  }

  /** Selects all rows if they are not all selected; otherwise clear selection. */
  masterToggle() {
    this.isAllSelected() ?
      this.selection.clear() :
      this.dataSource.data.forEach(row => this.selection.select(row));
  }


}


@Component({
  selector: 'resource-edit-dialogue',
  templateUrl: 'ResourceEditDialog.html',
})

export class ResourceEditDialog {

  constructor(

    public dialogRef: MatDialogRef<ResourceEditDialog>,
    @Inject(MAT_DIALOG_DATA) public data: Resource) {
      console.log(data);
     }

  onNoClick(): void {
    this.dialogRef.close();
  }

}
