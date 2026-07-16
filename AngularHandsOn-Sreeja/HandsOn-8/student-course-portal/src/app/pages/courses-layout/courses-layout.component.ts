import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-courses-layout',
  standalone: true,
  imports: [CommonModule, RouterOutlet],
  templateUrl: './courses-layout.component.html',
  styleUrl: './courses-layout.component.css'
})
export class CoursesLayoutComponent {
  // Layout component for nested routes
}