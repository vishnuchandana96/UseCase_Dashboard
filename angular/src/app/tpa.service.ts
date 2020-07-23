import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { TPA} from './modals/TPA';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class TpaService {

  constructor(private httpClient : HttpClient) { }

  public url="http://localhost:8097/api/v1";

  addTPA(tpa : TPA) : Observable<TPA>
  {
  console.log("Sendind data to backend"+tpa);
  return this.httpClient.post<TPA>(this.url+"/tpa",tpa);
  }
  getAllTPAs() : Observable<TPA[]>
  {
    return this.httpClient.get<TPA[]>(this.url+"/tpa");
  }

  deleteTPA(tpa_id:string) : Observable<TPA>
  {
    return this.httpClient.delete<TPA>(this.url+"/tpa/"+tpa_id);
  }
  editTPA(tpa : TPA) : Observable<TPA>
  {
    return this.httpClient.put<TPA>(this.url+"/tpa/"+tpa.tpa_id,tpa);
  }
}
