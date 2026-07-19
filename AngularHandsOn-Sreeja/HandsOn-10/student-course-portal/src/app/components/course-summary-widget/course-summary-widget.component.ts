import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CourseService } from '../../services/course.service';

@Component({
  selector: 'app-course-summary-widget',
  standalone: true,
  imports: [CommonModule],
  template: `
    <div class="summary-widget">
      <h4>Course Summary</h4>
      <p>Total Courses: {{ courseCount }}</p>
      <p>Last Updated: {{ lastUpdated | date:'medium' }}</p>
    </div>
  `,
  styles: [`
    .summary-widget {
      padding: 15px;
      background-color: #f8f9fa;
      border-radius: 8px;
      border-left: 4px solid #3498db;
    }
    .summary-widget h4 {
      margin: 0 0 10px 0;
      color: #2c3e50;
    }
    .summary-widget p {
      margin: 5px 0;
      color: #555;
    }
  `]
})
export class CourseSummaryWidgetComponent {
  courseCount = 0;
  lastUpdated = new Date();

  constructor(private courseService: CourseService) {
    this.courseService.getCourses().subscribe(courses => {
      this.courseCount = courses.length;
      this.lastUpdated = new Date();
    });
  }
}