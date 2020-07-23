import { Component, OnInit } from '@angular/core';
import {FormControl} from '@angular/forms';
import { NgForm } from '@angular/forms';
import { TpaService } from '../tpa.service';
import { ResourceService } from '../resource.service';
import {Resource} from '../modals/Resource';
import { ClusterService } from '../cluster.service';
import { ApplicationService } from '../application.service';

@Component({
  selector: 'app-tpa',
  templateUrl: './tpa.component.html',
  styleUrls: ['./tpa.component.scss']
})
export class TpaComponent implements OnInit {

  public resourceList : Resource[];
  public applicationdata : string[];
  public clusterdata : string[];

  constructor(private tpaService : TpaService, private resourceService : ResourceService , private clusterService : ClusterService , private applicationService : ApplicationService){}

    ngOnInit(){
      this.getApplicationsdata();
      this.getClustersdata();
    }

    status = new FormControl();
    statusList: string[] = ['Assigned', 'Ready for Acceptance', 'Ready for Decision', 'Reviewed', 'Work in Progress'];

    getClustersdata()
    {
      return this.clusterService.getAllClusters().subscribe(
        data => this.clusterdata = data.map(x => x.cluster_name));
    }
    
    getApplicationsdata()
    {
      return this.applicationService.getApplications().subscribe(
        data => this.applicationdata = data.map(x => x.application_name));
    }
    
  addTPA(form: NgForm) {
    let tpa = form.value
    this.tpaService.addTPA(tpa).subscribe(
      (data) => {
        console.log(data)
        alert("TPA Added Successfully")
        form.reset();
      })
     
       
  }
}