import { Component, OnInit , Inject , ViewChild} from '@angular/core';
import { Router } from '@angular/router';
import { TpaService } from '../tpa.service';
import { TPA } from '../modals/TPA';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import {Table} from '../modals/Table';
import { SelectionModel } from '@angular/cdk/collections';
import { MatTableDataSource, MatPaginator} from '@angular/material';


@Component({
  selector: 'app-tpa-view',
  templateUrl: './tpa-view.component.html',
  styleUrls: ['./tpa-view.component.scss']
})
export class TpaViewComponent implements OnInit {

  public tpaList : TPA[];
  public tpadata : TPA[]
  dataSource = new MatTableDataSource<TPA>(this.tpadata);
  selection = new SelectionModel<TPA>(true, []);

  @ViewChild(MatPaginator, {static : false}) paginator: MatPaginator;

 constructor(private router:Router , private tpaService : TpaService, private dialog : MatDialog ) { }

  ngOnInit() 
  {
    this.getTPA();
  }


  exportTable()
  {
    //Table.find('.update_th, .update_td').remove();
    Table.exportToExcel("tpa"); 
  }

  
  public doFilter = (value: string) => {
    this.dataSource.filter = value.trim().toLowerCase();
  }
  
  displayedColumns : string[] = ['select','cluster_name','application_name','tpa','tpa_status','tpa_pd','delivery_date','update','delete'];
  
  getTPA()
  {
    this.tpaService.getAllTPAs().subscribe(data => {
      this.tpaList = data;
      this.tpadata = Object.assign(this.tpaList);
      this.dataSource = new MatTableDataSource<TPA>(this.tpadata);
      this.selection = new SelectionModel<TPA>(true, []);
      this.dataSource.paginator= this.paginator;
    })

  }


  editDialog(tpa : TPA) {
    console.log(tpa);
      const dialogRef = this.dialog.open(TPAEditDialog,
        {
          width: '300px',
          height: 'auto',
          data: {tpa}
        });
      dialogRef.afterClosed().subscribe(result => {
        if (result != undefined) {
          this.editTPA(result);
        }
        else
        {
          this.getTPA();
        }
      });
    }
      
    editTPA(tpa : TPA)
    {
      console.log(tpa);
      this.tpaService.editTPA(tpa).subscribe(
        (data) => {
          console.log(data)
          this.getTPA();
        })
    }
  
    deleteTPA(tpa : TPA)
    {
      tpa.isdeleted = true;
      this.tpaService.editTPA(tpa).subscribe(
        (data) => {
          console.log(data)
          this.getTPA();
        }) 
    }

    isAllSelected() {
      const numSelected = this.selection.selected.length;
      const numRows = this.dataSource.data.length;
      return numSelected === numRows;
    }
  
    removeSelectedRows() {
      this.selection.selected.forEach(item => {
        this.deleteTPA(this.tpadata.find(x => x === item));
      });
      this.selection = new SelectionModel<TPA>(true, []);
    }
  
    /** Selects all rows if they are not all selected; otherwise clear selection. */
    masterToggle() {
      this.isAllSelected() ?
        this.selection.clear() :
        this.dataSource.data.forEach(row => this.selection.select(row));
    }
  }
  
  
  @Component({
    selector: 'tpa-edit-dialogue',
    templateUrl: 'TPAEditDialog.html',
  })
  
  export class TPAEditDialog {
  
    constructor(
  
      public dialogRef: MatDialogRef<TPAEditDialog>,
      @Inject(MAT_DIALOG_DATA) public data: TPA) {
        console.log(data);
       }
  
    onNoClick(): void {
      this.dialogRef.close();
    }
}





