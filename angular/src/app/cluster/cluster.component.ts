import { Component, OnInit } from '@angular/core';
import { ClusterService } from '../cluster.service';
import {Cluster} from '../modals/Cluster';
import { NgForm, Form } from '@angular/forms';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-cluster',
  templateUrl: './cluster.component.html',
  styleUrls: ['./cluster.component.scss']
})
export class ClusterComponent implements OnInit {

  public clustersList : Cluster[];
  constructor(private clusterService:ClusterService, private datepipe : DatePipe) { }

  ngOnInit() {

  }

  getClusters()
  {
  return this.clusterService.getAllClusters().subscribe(
    (data) => {
      console.log(data)
     // this.datepipe.transform(data.map(x => x.cluster_date), 'yyyy-mm-dd');
      this.clustersList = data
   })
  }

  addCluster(form : NgForm)
  {
    let cluster = form.value
    this.clusterService.addCluster(cluster).subscribe(
      (data) => {
        console.log(data)
        alert("Cluster Added Successfully")
        form.reset();
      },
      (error) => {
          if(error.status == 409){
          alert("Cluster Already Exist")}});
          }
}
