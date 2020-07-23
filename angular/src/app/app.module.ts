import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { MatToolbarModule, MatIconModule, MatSidenavModule, MatListModule, MatButtonModule } from  '@angular/material';
import {MatCardModule} from '@angular/material/card';
import {MatDatepickerModule} from '@angular/material/datepicker';
import {MatFormFieldModule} from '@angular/material/form-field';
import {  MatInputModule,MatSelectModule } from '@angular/material';
import { HttpClientModule } from '@angular/common/http';
import { FlexLayoutModule } from "@angular/flex-layout";
import {MatGridListModule} from '@angular/material/grid-list';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {MatTooltipModule} from '@angular/material/tooltip';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatNativeDateModule } from '@angular/material/core';
import { MatDialogModule } from '@angular/material/dialog';
import {MatMenuModule} from '@angular/material/menu';
import {MatTableModule} from '@angular/material/table';
import {MatCheckboxModule} from '@angular/material/checkbox';
import { MatSortModule } from '@angular/material';
import {MatPaginatorModule} from '@angular/material/paginator';

import { ApplicationComponent } from './application/application.component';
import { ClustersViewComponent } from './clusters-view/clusters-view.component';
import { ApplicationsViewComponent } from './applications-view/applications-view.component';
import { SidenavComponent } from './sidenav/sidenav.component';
import { ClusterComponent } from './cluster/cluster.component';
import { ClusterEditDialog } from '../app/clusters-view/clusters-view.component';
import { ApplicationEditDialog } from '../app/applications-view/applications-view.component';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { ResourceComponent } from './resource/resource.component';
import { ResourceViewComponent, ResourceEditDialog } from './resource-view/resource-view.component';
import { TpaComponent } from './tpa/tpa.component';
import { TpaViewComponent, TPAEditDialog } from './tpa-view/tpa-view.component';
import { DatePipe } from '@angular/common';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    SidenavComponent,
    ClusterComponent,
    ApplicationComponent,
    ClustersViewComponent,
    ApplicationsViewComponent,
    ClusterEditDialog,
    ApplicationEditDialog,
    ResourceComponent,
    ResourceViewComponent,
    ResourceEditDialog,
    TpaComponent,
    TpaViewComponent,
    TPAEditDialog
  ],
  imports: [
    MatDialogModule,
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatToolbarModule,
    MatIconModule,
    MatSidenavModule,
    MatCardModule,
    MatDatepickerModule,
    MatFormFieldModule,
    MatNativeDateModule,
    MatInputModule,
    MatSelectModule,
    HttpClientModule,
    FlexLayoutModule,
    MatGridListModule,
    FontAwesomeModule,
    FormsModule,
    ReactiveFormsModule,
    MatTooltipModule,
    MatButtonModule,
    MatMenuModule,
    MatTableModule,
    MatListModule,
    MatCheckboxModule,
    MatSortModule,
    MatPaginatorModule
  ],
  providers: [ MatDatepickerModule,MatNativeDateModule, DatePipe],
  bootstrap: [AppComponent]
})
export class AppModule { }


