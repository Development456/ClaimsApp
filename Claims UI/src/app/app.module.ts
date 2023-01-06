import { AddClaimComponent, BottomSheetOverviewExampleSheet, DialogBoxComponent } from './components/add-claims/add-claims.component';
import { DataTableComponent, DataTableOrdersComponent } from './components/data-table/data-table.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';
import { AuthGuard } from './auth.guard';
import { BarChartComponent } from './components/bar-chart/bar-chart.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { BrowserModule } from '@angular/platform-browser';
import { ChartsModule } from 'ng2-charts';
import { ClaimsApiService } from './claims-api.service';
import { ClaimsDetailsComponent } from './components/claims-details/claims-details.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { DataCardsComponent } from './components/data-cards/data-cards.component';
import { DatePipe } from '@angular/common';
import { DetailsModalComponent } from './components/data-table/details-modal/details-modal.component';
import { DoughnutChartComponent } from './components/doughnut-chart/doughnut-chart.component';
import { FlexLayoutModule } from '@angular/flex-layout';
import { HeaderComponent } from './components/header/header.component';
import { HttpClientModule } from '@angular/common/http';
import { LoginComponent } from './components/login/login.component';
import { MatAutocompleteModule } from '@angular/material/autocomplete';
import { MatBottomSheetModule } from '@angular/material/bottom-sheet';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatDialogModule } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatGridListModule } from '@angular/material/grid-list';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatListModule } from '@angular/material/list';
import { MatMenuModule } from '@angular/material/menu';
import { MatNativeDateModule } from '@angular/material/core';
import { MatProgressBarModule } from '@angular/material/progress-bar';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatSelectModule } from '@angular/material/select';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatSlideToggleModule } from '@angular/material/slide-toggle';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { MatStepperModule } from '@angular/material/stepper';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatTooltipModule } from '@angular/material/tooltip';
import { NgModule } from '@angular/core';
import { NgxDatatableModule } from '@swimlane/ngx-datatable';
import { NgxPrintModule } from 'ngx-print';
import { ToastrModule } from 'ngx-toastr';

@NgModule({
  declarations: [
    AppComponent,
    DashboardComponent,
    DataTableComponent,
    DoughnutChartComponent,
    BarChartComponent,
    DataCardsComponent,
    HeaderComponent,
    AddClaimComponent,
    LoginComponent,
    DetailsModalComponent,
    ClaimsDetailsComponent,
    DialogBoxComponent,
    DataTableOrdersComponent,
    BottomSheetOverviewExampleSheet
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatCardModule,
    FlexLayoutModule,
    ChartsModule,
    MatToolbarModule,
    MatButtonModule,
    MatCheckboxModule,
    FormsModule,
    MatMenuModule,
    MatIconModule,
    MatSidenavModule,
    MatListModule,
    MatStepperModule,
    MatFormFieldModule,
    ReactiveFormsModule,
    HttpClientModule,
    MatNativeDateModule,
    MatInputModule,
    MatDatepickerModule, MatBottomSheetModule,
    NgxDatatableModule,
    MatSlideToggleModule,
    NgxPrintModule,
    MatDialogModule,
    MatProgressBarModule,
    MatTooltipModule,
    MatAutocompleteModule,
    MatSnackBarModule,
    MatProgressSpinnerModule,
    HttpClientModule,
    MatGridListModule,
    MatSelectModule,
    ToastrModule.forRoot({
      closeButton: true,
      timeOut: 15000, // 15 seconds
      progressBar: true,
    }),
    MatDatepickerModule,
    MatBottomSheetModule,
    NgxDatatableModule,
    MatSlideToggleModule,
    NgxPrintModule,
    MatDialogModule,
    MatProgressBarModule,
    MatTooltipModule,
    MatAutocompleteModule,
    MatSnackBarModule,
    MatProgressSpinnerModule,
    HttpClientModule,
    MatGridListModule,
    MatSelectModule
  ],
  providers: [ClaimsApiService, AuthGuard, DatePipe],
  bootstrap: [AppComponent]
})
export class AppModule { }
