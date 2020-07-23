import { Component, OnInit } from '@angular/core';
import { Application } from '../modals/Application';
import { ApplicationService } from '../application.service';
import { mapToMapExpression } from '@angular/compiler/src/render3/util';
import { ClusterService } from '../cluster.service';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-application',
  templateUrl: './application.component.html',
  styleUrls: ['./application.component.scss']
})

export class ApplicationComponent implements OnInit {

  constructor(private applicationService: ApplicationService, private clusterService: ClusterService) { }

  public clusterList: string[];

  ngOnInit() {
    this.getClusters();
  }

  addApplication(form: NgForm) {
    let application = form.value
    this.applicationService.addApplication(application).subscribe(
      (data) => {
        console.log(data)
        alert("Application Added Successfully")
        form.reset();
      },
      (error) => {
          if(error.status == 409){
          alert("Application Already Exist")}});
          
  }

  getClusters() {
    this.clusterService.getAllClusters().subscribe(
      (data) => {
        this.clusterList = data.map(x => x.cluster_name)
      })
  }
}
