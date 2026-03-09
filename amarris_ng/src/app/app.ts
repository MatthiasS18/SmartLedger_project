import { Component, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { DashBoard } from '../page/dashBoard/DashBoard.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, DashBoard],
  templateUrl: './app.html'
})
export class App {
  protected readonly title = signal('amarris_ng');
}
