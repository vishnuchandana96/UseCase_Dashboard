import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Resource} from './modals/Resource';
import { Observable } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ResourceService {

  public url="http://localhost:8097/api/v1";

  constructor(private httpclient:HttpClient) { }

  addResource(resource : Resource) : Observable<Resource>
  {
    return this.httpclient.post<Resource>(this.url+"/resource",resource)
      .pipe(catchError((error: any) => {
        console.log(error);
        return throwError(error)
      }
      ));
  }
  editResource(resource : Resource) : Observable<Resource>
  {
    console.log(resource.emp_sno);
    return this.httpclient.put<Resource>(this.url+"/resource/"+resource.emp_sno,resource);
  }
  
  deleteResource(emp_sno : string) : Observable<Resource>
  {
    return this.httpclient.delete<Resource>(this.url+"/resource/"+emp_sno);
  }
  getAllResources() : Observable<Resource[]>
  {
    return this.httpclient.get<Resource[]>(this.url+"/resource");
  }
}
  

