import React from 'react';
import './CohortDetails.css';

function CohortDetails({ cohort, cohorts }) {
    if (cohorts && cohorts.length > 0) {
        return (
            <div className="cohorts-container">
                <h2 className="cohorts-title">Cohort Dashboard</h2>
                {cohorts.map((c, index) => (
                    <div key={index} className="cohort-card">
                        <h3>{c.cohortCode}</h3>
                        <h4>{c.cohortName}</h4>
                        <p><strong>Start Date:</strong> {c.startDate}</p>
                        <p><strong>End Date:</strong> {c.endDate}</p>
                        <p><strong>Status:</strong> 
                            <span className={`status-badge status-${c.status}`}>
                                {c.status}
                            </span>
                        </p>
                        <p><strong>Total Students:</strong> {c.totalStudents}</p>
                        {c.trainer && <p><strong>Trainer:</strong> {c.trainer}</p>}
                    </div>
                ))}
            </div>
        );
    }

    if (cohort) {
        return (
            <div className="cohort-details">
                <h2 className="cohorts-title">Cohort Details</h2>
                <div className="cohort-card">
                    <h3>{cohort.cohortCode}</h3>
                    <h4>{cohort.cohortName}</h4>
                    <p><strong>Start Date:</strong> {cohort.startDate}</p>
                    <p><strong>End Date:</strong> {cohort.endDate}</p>
                    <p><strong>Status:</strong> 
                        <span className={`status-badge status-${cohort.status}`}>
                            {cohort.status}
                        </span>
                    </p>
                    <p><strong>Total Students:</strong> {cohort.totalStudents}</p>
                    {cohort.trainer && <p><strong>Trainer:</strong> {cohort.trainer}</p>}
                </div>
            </div>
        );
    }

    return <div className="no-data">No cohort data available</div>;
}

export default CohortDetails;