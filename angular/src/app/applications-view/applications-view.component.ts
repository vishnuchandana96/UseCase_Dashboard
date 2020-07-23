import { Component, OnInit,Inject , ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { Application } from '../modals/Application';
import { ApplicationService } from '../application.service';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import {Table} from '../modals/Table';
import { SelectionModel } from '@angular/cdk/collections';
import { MatTableDataSource, MatPaginator} from '@angular/material';

@Component({
  selector: 'app-applications-view',
  templateUrl: './applications-view.component.html',
  styleUrls: ['./applications-view.component.scss']
})
export class ApplicationsViewComponent implements OnInit {

  public applicationList:Application[];
  public applicationdata : Application[];

  dataSource = new MatTableDataSource<Application>(this.applicationdata);
  selection = new SelectionModel<Application>(true, []);

  @ViewChild(MatPaginator, {static : false}) paginator: MatPaginator;

  constructor(private router : Router, private applicationService:ApplicationService, private dialog : MatDialog) { }

  ngOnInit() {
    this.getApplications();
  }

  
  displayedColumns : string[] = ['select','cluster_name','application_name','application_description','update','delete']

  exportTable(){
    Table.exportToExcel("application");
  }

  public doFilter = (value: string) => {
    this.dataSource.filter = value.trim().toLowerCase();
  }
  
  createApplication()
  {
      this.router.navigateByUrl('application');
  }
  getApplications()
  {
    this.applicationService.getApplications().subscribe(data => {
      this.applicationList = data;
      this.applicationdata = Object.assign(this.applicationList);
      this.dataSource = new MatTableDataSource<Application>(this.applicationdata);
      this.selection = new SelectionModel<Application>(true, []);
      this.dataSource.paginator = this.paginator;
    })
  }

  editDialog(application : Application) {
  console.log(application);
    const dialogRef = this.dialog.open(ApplicationEditDialog,
      {
        width: '300px',
        height: 'auto',
        data: {application}
      });
    dialogRef.afterClosed().subscribe(result => {
      if (result != undefined) {
        this.editApplication(result);
      }
      else{
        this.getApplications();
      }
    });
  }

  editApplication(application : Application)
  {
    console.log(application);
    this.applicationService.editApplication(application).subscribe(
      (data) => {
        console.log(data)
        this.getApplications();
      })
  }

  deleteApplication(application : Application)
  {
    application.isdeleted = true;
    this.applicationService.editApplication(application).subscribe(
      (data) => {
        console.log(data)
        this.getApplications();
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
      this.deleteApplication(this.applicationdata.find(x => x === item));
    });
    this.selection = new SelectionModel<Application>(true, []);
  }

  /** Selects all rows if they are not all selected; otherwise clear selection. */
  masterToggle() {
    this.isAllSelected() ?
      this.selection.clear() :
      this.dataSource.data.forEach(row => this.selection.select(row));
  }
}


@Component({
  selector: 'application-edit-dialogue',
  templateUrl: 'ApplicationEditDialog.html',
})

export class ApplicationEditDialog {

  constructor(

    public dialogRef: MatDialogRef<ApplicationEditDialog>,
    @Inject(MAT_DIALOG_DATA) public data: Application) {
      console.log(data);
     }

  onNoClick(): void {
    this.dialogRef.close();
  }

}
