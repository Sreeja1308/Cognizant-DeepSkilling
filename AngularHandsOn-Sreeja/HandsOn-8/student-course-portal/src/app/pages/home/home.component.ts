import { Component, OnInit, OnDestroy } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Subscription } from 'rxjs';
import { CourseService } from '../../services/course.service';
import { NotificationComponent } from '../../components/notification/notification.component';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [CommonModule, FormsModule, NotificationComponent],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent implements OnInit, OnDestroy {
  portalName = 'Student Course Portal';
  isPortalActive = true;
  message = '';
  searchTerm = '';
  courseCount = 0;
  private subscription: Subscription | null = null;

  constructor(private courseService: CourseService) {}

  ngOnInit(): void {
    // Subscribe to get courses count from the observable
    this.subscription = this.courseService.getCourses().subscribe({
      next: (courses) => {
        this.courseCount = courses.length;
        console.log('HomeComponent initialised — courses loaded, count:', this.courseCount);
      },
      error: (err) => {
        console.error('Error loading courses count:', err);
        this.courseCount = 0;
      }
    });
  }

  ngOnDestroy(): void {
    // Unsubscribe to prevent memory leaks
    if (this.subscription) {
      this.subscription.unsubscribe();
    }
    console.log('HomeComponent destroyed');
  }

  onEnrollClick(): void {
    this.message = 'Enrollment opened!';
  }
}