import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';


import { AppComponent } from './app.component';
import { PaymentComponent } from './payment/payment.component';
import { BillerComponent } from './biller/biller.component';
import { CustomerComponent } from './customer/customer.component';


@NgModule({
  declarations: [
    AppComponent,
    PaymentComponent,
    BillerComponent,
    CustomerComponent
  ],
  imports: [
    BrowserModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
