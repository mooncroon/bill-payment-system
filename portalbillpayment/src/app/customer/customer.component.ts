import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { Customer } from '../models/customer.model';
import { UserService } from './customer.service';

@Component({
  selector: 'app-customer',
  templateUrl: './customer.component.html',
  styleUrls: ['./customer.component.css']
})
export class CustomerComponent implements OnInit {

  customers: Customer[];

  constructor(private router: Router, private userService: UserService) {
  }
  ngOnInit() {
    this.customerService.getUsers()
      .subscribe( data => {
        this.customers = data;
      });
  }
  deleteCustomer(customer: Customer): void {
    this.customerService.deleteCustomer(Customer)
      .subscribe( data => {
        this.customers = this.customers.filter(u => u !== customer);
      })
  };

}
