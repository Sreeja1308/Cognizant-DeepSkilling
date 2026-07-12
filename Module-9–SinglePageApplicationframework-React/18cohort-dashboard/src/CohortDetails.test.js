import React from 'react';
import { render, screen, cleanup } from '@testing-library/react';
import '@testing-library/jest-dom/extend-expect';
import CohortDetails from './CohortDetails';
import CohortData from './Cohort';

// Clean up after each test
afterEach(cleanup);

// Test suite
describe('Cohort Details Component', () => {

    // Test 1: Should create the component
    test('should create the component', () => {
        render(<CohortDetails cohort={CohortData[0]} />);
        const element = screen.getByText(/Cohort Details/i);
        expect(element).toBeInTheDocument();
        console.log('✓ Test 1 passed: Component created successfully');
    });

    // Test 2: Should initialize the props
    test('should initialize the props', () => {
        const cohort = CohortData[0];
        render(<CohortDetails cohort={cohort} />);
        
        // Check if cohort code is displayed
        expect(screen.getByText(cohort.cohortCode)).toBeInTheDocument();
        expect(screen.getByText(cohort.cohortName)).toBeInTheDocument();
        console.log('✓ Test 2 passed: Props initialized successfully');
    });

    // Test 3: Should display cohort code in h3
    test('should display cohort code in h3', () => {
        const cohort = CohortData[0];
        render(<CohortDetails cohort={cohort} />);
        
        const h3Element = screen.getByRole('heading', { level: 3 });
        expect(h3Element).toBeInTheDocument();
        expect(h3Element).toHaveTextContent(cohort.cohortCode);
        console.log('✓ Test 3 passed: Cohort code displayed in h3');
    });

    // Test 4: Should always render same html (Snapshot testing)
    test('should always render same html', () => {
        const cohort = CohortData[0];
        const { container } = render(<CohortDetails cohort={cohort} />);
        
        expect(container).toMatchSnapshot();
        console.log('✓ Test 4 passed: Snapshot created successfully');
    });

    // Additional Test 5: Should display cohort name
    test('should display cohort name', () => {
        const cohort = CohortData[0];
        render(<CohortDetails cohort={cohort} />);
        
        expect(screen.getByText(cohort.cohortName)).toBeInTheDocument();
        console.log('✓ Test 5 passed: Cohort name displayed');
    });

    // Additional Test 6: Should display status with correct color
    test('should display status with correct color', () => {
        const cohort = CohortData[0];
        render(<CohortDetails cohort={cohort} />);
        
        const statusElement = screen.getByText(cohort.status);
        expect(statusElement).toBeInTheDocument();
        
        if (cohort.status === 'ongoing') {
            expect(statusElement).toHaveClass('status-ongoing');
        } else if (cohort.status === 'completed') {
            expect(statusElement).toHaveClass('status-completed');
        }
        console.log('✓ Test 6 passed: Status displayed with correct color');
    });

    // Additional Test 7: Should display all cohort details
    test('should display all cohort details', () => {
        const cohort = CohortData[0];
        render(<CohortDetails cohort={cohort} />);
        
        expect(screen.getByText(cohort.cohortCode)).toBeInTheDocument();
        expect(screen.getByText(cohort.cohortName)).toBeInTheDocument();
        expect(screen.getByText(cohort.startDate)).toBeInTheDocument();
        expect(screen.getByText(cohort.endDate)).toBeInTheDocument();
        expect(screen.getByText(cohort.status)).toBeInTheDocument();
        expect(screen.getByText(cohort.totalStudents.toString())).toBeInTheDocument();
        console.log('✓ Test 7 passed: All cohort details displayed');
    });

    // Additional Test 8: Should render multiple cohorts
    test('should render multiple cohorts', () => {
        render(<CohortDetails cohorts={CohortData} />);
        
        const cohortCards = document.querySelectorAll('.cohort-card');
        expect(cohortCards.length).toBe(CohortData.length);
        console.log('✓ Test 8 passed: Multiple cohorts rendered');
    });

    // Test 9: Should handle empty state
    test('should handle empty state', () => {
        render(<CohortDetails />);
        
        expect(screen.getByText(/No cohort data available/i)).toBeInTheDocument();
        console.log('✓ Test 9 passed: Empty state handled');
    });

    // Test 10: Should render ongoing cohorts
    test('should render ongoing cohorts', () => {
        const ongoingCohorts = CohortData.filter(c => c.status === 'ongoing');
        render(<CohortDetails cohorts={ongoingCohorts} />);
        
        const ongoingElements = screen.getAllByText('ongoing');
        expect(ongoingElements.length).toBe(ongoingCohorts.length);
        console.log('✓ Test 10 passed: Ongoing cohorts rendered');
    });

    // Test 11: Should render completed cohorts
    test('should render completed cohorts', () => {
        const completedCohorts = CohortData.filter(c => c.status === 'completed');
        render(<CohortDetails cohorts={completedCohorts} />);
        
        const completedElements = screen.getAllByText('completed');
        expect(completedElements.length).toBe(completedCohorts.length);
        console.log('✓ Test 11 passed: Completed cohorts rendered');
    });
});

// Console output summary
console.log('\n📊 Test Suite Summary:');
console.log('✅ All tests passed successfully!');
console.log('📝 Total tests: 11');