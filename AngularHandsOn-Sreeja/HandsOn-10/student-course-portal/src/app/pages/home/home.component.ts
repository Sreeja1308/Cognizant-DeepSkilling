import { Component, OnInit, OnDestroy, ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { CourseService } from '../../services/course.service';
import { LoadingService } from '../../services/loading.service';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit, OnDestroy {
  portalName = 'Student Course Portal';
  isPortalActive = true;
  message = '';
  searchTerm = '';
  courseCount = 0;
  enrolledCount = 3;
  gpa = 3.8;
  private intervalId: any;

  constructor(
    private courseService: CourseService,
    private loadingService: LoadingService,
    private cdr: ChangeDetectorRef
  ) {}

  ngOnInit(): void {
    console.log('HomeComponent initialised — courses loaded');
    this.loadingService.show();
    this.courseService.getCourses().subscribe({
      next: (courses) => {
        this.courseCount = courses.length;
        this.loadingService.hide();
        this.cdr.detectChanges();
      },
      error: () => {
        this.loadingService.hide();
        this.cdr.detectChanges();
      }
    });

    this.intervalId = setInterval(() => {
      console.log('HomeComponent heartbeat');
    }, 5000);
  }

  ngOnDestroy(): void {
    console.log('HomeComponent destroyed');
    if (this.intervalId) {
      clearInterval(this.intervalId);
    }
  }

  onEnrollClick(): void {
    this.message = 'Enrollment opened!';
  }
}