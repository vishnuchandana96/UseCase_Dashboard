import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ClusterComponent } from './cluster/cluster.component';
import { ApplicationComponent } from './application/application.component';
import { ClustersViewComponent, ClusterEditDialog } from './clusters-view/clusters-view.component';
import { ApplicationsViewComponent, ApplicationEditDialog } from './applications-view/applications-view.component';
import { SidenavComponent } from './sidenav/sidenav.component';
import { ResourceComponent } from './resource/resource.component';
import { ResourceViewComponent, ResourceEditDialog } from './resource-view/resource-view.component';
import { TpaComponent } from './tpa/tpa.component';
import { TpaViewComponent, TPAEditDialog } from './tpa-view/tpa-view.component';

const routes: Routes = [
  {
    path : 'cluster', component : ClusterComponent    
  },
  {
    path : 'application' , component:ApplicationComponent
  },
  {
    path:'', component : ClusterComponent
  },
  {
    path:'cluster-view', component:ClustersViewComponent
  },
  {
    path:'application-view', component:ApplicationsViewComponent
  },
  {
    path : 'sidenav', component:SidenavComponent
  },
  {
    path : 'resource' , component:ResourceComponent
  },
  {
    path:'resource-view',component:ResourceViewComponent
  },
  {
    path:'tpa' , component:TpaComponent
  },

{
  path:'tpa-view',component:TpaViewComponent
}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
  entryComponents : [ClustersViewComponent, ClusterEditDialog, ApplicationsViewComponent,ApplicationEditDialog,ResourceViewComponent,ResourceEditDialog,TpaViewComponent,TPAEditDialog]
})
export class AppRoutingModule { }
