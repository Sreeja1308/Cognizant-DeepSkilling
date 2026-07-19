import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-courses-layout',
  standalone: true,
  imports: [RouterOutlet],
  template: `
    <div class="courses-layout">
      <router-outlet></router-outlet>
    </div>
  `,
  styles: [`
    .courses-layout {
      padding: 20px;
    }
  `]
})
export class CoursesLayoutComponent {}