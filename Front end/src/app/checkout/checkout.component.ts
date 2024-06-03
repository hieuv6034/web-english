import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { loadStripe } from '@stripe/stripe-js';
import {environment} from "../Form/environment";


@Component({
  selector: 'app-checkout',
  templateUrl: './checkout.component.html',
  styleUrls: ['./checkout.component.css'],
})
export class CheckoutComponent {
  // We load  Stripe
  stripePromise = loadStripe(environment.stripe);
  constructor(private http: HttpClient) {}
  yearlyPriceId = "price_1LPnAUFqvipCbJY9npXk8QiZ";
  async pay(): Promise<void> {
    this.checkout(this.yearlyPriceId);
  }

  private async checkout(priceId: string): Promise<void> {
    const checkout = {
      priceId: priceId,
      cancelUrl: 'http://localhost:4200/cancel',
      successUrl: 'http://localhost:4200/success/',
    };
    const stripe = await this.stripePromise;
    // this is a normal http calls for a backend api
    this.http
      .post(`${environment.serverUrl}/subscription`, checkout)
      .subscribe((data: any) => {
        // I use stripe to redirect To Checkout page of Stripe platform
        stripe.redirectToCheckout({
          sessionId: data.sessionId,
        });
      });
  }
}
