import { Component, OnInit } from '@angular/core';
import { ResourceService } from '../resource.service';
import {Resource} from '../modals/Resource';
import { Cluster } from '../modals/Cluster';
import {Application} from '../modals/Application';
import { NgForm } from '@angular/forms';
import { ClusterService } from '../cluster.service';
import { ApplicationService } from '../application.service';

@Component({
  selector: 'app-resource',
  templateUrl: './resource.component.html',
  styleUrls: ['./resource.component.scss']
})
export class ResourceComponent implements OnInit {

  public resourceList: Resource[]

  public applicationdata : string[]

  public clusterdata : string[]

  constructor(private resourceService : ResourceService, private clusterService : ClusterService , private applicationService : ApplicationService) { }

  ngOnInit() 
  {
    this.getClustersdata();
    this.getApplicationsdata();
  }


  getResources()
  {
  return this.resourceService.getAllResources().subscribe(
    (data) => {
      console.log(data)
      this.resourceList = data
   })
  }

//   getApplicationsdata()
//   {
//     return this.resourceService.getAllResources().subscribe(
//       (data) => {
//         console.log(data)
//         this.resourceList = data
//         this.applicationdata = data.map(x => x.application_name)
//   })
// }

// getClustersdata()
// {
//   return this.resourceService.getAllResources().subscribe(
//     (data) => {
//       console.log(data)
//       this.resourceList = data
//   this.clusterdata = data.map(x=>x.cluster_name)
// })
// }

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


  addResource(form : NgForm)
  {
    let resource = form.value
    console.log(resource);
    this.resourceService.addResource(resource).subscribe(
      (data) => {
        console.log(data)
        alert("Resource Added Successfully")
        form.reset();
      },
      (error) => {
          if(error.status == 409){
          alert("Resource Already Exist")}});
          }     
      
  }
