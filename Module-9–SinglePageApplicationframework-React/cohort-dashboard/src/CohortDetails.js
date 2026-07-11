import React from 'react';
import styles from './CohortDetails.module.css';

function CohortDetails({ cohorts }) {
    const getStatusStyle = (status) => {
        if (status.toLowerCase() === 'ongoing') {
            return styles.ongoing;
        }
        return styles.completed;
    };

    const getStatusBadge = (status) => {
        if (status.toLowerCase() === 'ongoing') {
            return `${styles.statusBadge} ${styles.statusOngoing}`;
        }
        return `${styles.statusBadge} ${styles.statusCompleted}`;
    };

    if (!cohorts || cohorts.length === 0) {
        return (
            <div className={styles.cohortContainer}>
                <p>No cohorts available</p>
            </div>
        );
    }

    return (
        <div>
            <h1 className={styles.header}>Cohort Dashboard</h1>
            <div className={styles.cohortContainer}>
                {cohorts.map((cohort, index) => (
                    <div key={index} className={styles.box}>
                        <h3 className={getStatusStyle(cohort.status)}>
                            {cohort.name}
                        </h3>
                        <dl>
                            <dt>Cohort ID</dt>
                            <dd>{cohort.id}</dd>
                            
                            <dt>Start Date</dt>
                            <dd>{cohort.startDate}</dd>
                            
                            <dt>End Date</dt>
                            <dd>{cohort.endDate}</dd>
                            
                            <dt>Status</dt>
                            <dd>
                                <span className={getStatusBadge(cohort.status)}>
                                    {cohort.status}
                                </span>
                            </dd>
                            
                            <dt>Total Students</dt>
                            <dd>{cohort.totalStudents}</dd>
                            
                            {cohort.trainer && (
                                <>
                                    <dt>Trainer</dt>
                                    <dd>{cohort.trainer}</dd>
                                </>
                            )}
                        </dl>
                    </div>
                ))}
            </div>
        </div>
    );
}

export default CohortDetails;