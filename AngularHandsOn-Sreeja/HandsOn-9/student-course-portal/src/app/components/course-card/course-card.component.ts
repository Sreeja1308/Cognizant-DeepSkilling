import { Component, Input, Output, EventEmitter, OnChanges, SimpleChanges } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CreditLabelPipe } from '../../pipes/credit-label.pipe';
import { HighlightDirective } from '../../directives/highlight.directive';

@Component({
  selector: 'app-course-card',
  standalone: true,
  imports: [CommonModule, CreditLabelPipe, HighlightDirective],
  templateUrl: './course-card.component.html',
  styleUrl: './course-card.component.css'
})
export class CourseCardComponent implements OnChanges {
  @Input() course: any = null;
  @Input() enrolledIds: number[] = [];
  @Output() enrollRequested = new EventEmitter<number>();
  @Output() unenrollRequested = new EventEmitter<number>();
  isExpanded = false;

  ngOnChanges(changes: SimpleChanges): void {
    if (changes['course'] && this.course) {
      console.log('Course changed - Previous:', changes['course'].previousValue, 'Current:', changes['course'].currentValue);
    }
  }

  isEnrolled(): boolean {
    return this.course ? this.enrolledIds.includes(this.course.id) : false;
  }

  onEnroll(): void {
    if (this.course) {
      if (this.isEnrolled()) {
        this.unenrollRequested.emit(this.course.id);
      } else {
        this.enrollRequested.emit(this.course.id);
      }
    }
  }

  toggleExpand(): void {
    this.isExpanded = !this.isExpanded;
  }

  get cardClasses(): any {
    return {
      'card--enrolled': this.isEnrolled(),
      'card--full': this.course?.credits >= 4,
      'expanded': this.isExpanded
    };
  }

  get cardStyles(): any {
    const colorMap: any = {
      'passed': '#2ecc71',
      'failed': '#e74c3c',
      'pending': '#95a5a6'
    };
    return {
      'border-left': `4px solid ${colorMap[this.course?.gradeStatus] || '#95a5a6'}`
    };
  }
}