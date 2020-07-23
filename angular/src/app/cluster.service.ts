import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import { Observable } from 'rxjs';
import { Cluster } from './modals/Cluster';
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class ClusterService {

  public url="http://localhost:8095/api/v1";

  constructor(private httpclient:HttpClient) { }

  getAllClusters() : Observable<Cluster[]>
  {
   return this.httpclient.get<Cluster[]>(this.url + "/cluster");
  }

  addCluster(cluster : Cluster) : Observable<Cluster>
  {
    return this.httpclient.post<Cluster>(this.url+"/cluster",cluster)
    .pipe(catchError((error: any) => {
      console.log(error);
      return throwError(error)
    }
    ));
  }

  editCluster(cluster : Cluster) : Observable<Cluster>
  {
    console.log(cluster.cluster_id);
    return this.httpclient.put<Cluster>(this.url+"/cluster/"+cluster.cluster_id,cluster);
  }
  
  deleteCluster(clusterId : string) : Observable<Cluster>
  {
    return this.httpclient.delete<Cluster>(this.url+"/cluster/"+clusterId);
  }

}
