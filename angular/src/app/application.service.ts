import { Injectable, ApplicationModule } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import { Observable } from 'rxjs';
import { Application } from './modals/Application';


@Injectable({
  providedIn: 'root'
})
export class ApplicationService {

  public url="http://localhost:8095/api/v1";


  constructor(private httpclient:HttpClient) { }

  addApplication(application : Application) : Observable<Application>
  {
    return this.httpclient.post<Application>(this.url+"/application",application);
  }
  editApplication(application : Application) : Observable<Application>
  {
    console.log(application.application_id);
    return this.httpclient.put<Application>(this.url+"/application/"+application.application_id,application);
  }
  
  deleteApplication(application_id : string) : Observable<Application>
  {
    return this.httpclient.delete<Application>(this.url+"/application/"+application_id);
  }
  getApplications() : Observable<Application[]>
  {
    return this.httpclient.get<Application[]>(this.url+"/application");
  }

}
