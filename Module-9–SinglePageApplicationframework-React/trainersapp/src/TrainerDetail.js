import React from 'react';
import { useParams, Link } from 'react-router-dom';

function TrainerDetail({ trainers }) {
    const { id } = useParams();
    const trainerId = parseInt(id);
    const trainer = trainers.find(t => t.trainerId === trainerId);

    if (!trainer) {
        return (
            <div style={styles.container}>
                <h2>Trainer Not Found</h2>
                <Link to="/trainers" style={styles.backLink}>
                    ← Back to Trainers List
                </Link>
            </div>
        );
    }

    return (
        <div style={styles.container}>
            <Link to="/trainers" style={styles.backLink}>
                ← Back to Trainers List
            </Link>
            
            <div style={styles.card}>
                <h2 style={styles.name}>{trainer.name}</h2>
                
                <div style={styles.details}>
                    <div style={styles.detailItem}>
                        <strong>Trainer ID:</strong>
                        <span>{trainer.trainerId}</span>
                    </div>
                    
                    <div style={styles.detailItem}>
                        <strong>Email:</strong>
                        <span>{trainer.email}</span>
                    </div>
                    
                    <div style={styles.detailItem}>
                        <strong>Phone:</strong>
                        <span>{trainer.phone}</span>
                    </div>
                    
                    <div style={styles.detailItem}>
                        <strong>Technology:</strong>
                        <span style={styles.technology}>{trainer.technology}</span>
                    </div>
                    
                    <div style={styles.detailItem}>
                        <strong>Skills:</strong>
                        <div style={styles.skillsContainer}>
                            {trainer.skills.map((skill, index) => (
                                <span key={index} style={styles.skill}>
                                    {skill}
                                </span>
                            ))}
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
}

const styles = {
    container: {
        padding: '30px',
        maxWidth: '700px',
        margin: '0 auto'
    },
    backLink: {
        color: '#3498db',
        textDecoration: 'none',
        fontSize: '16px',
        display: 'inline-block',
        marginBottom: '20px'
    },
    card: {
        backgroundColor: 'white',
        borderRadius: '10px',
        boxShadow: '0 2px 10px rgba(0,0,0,0.1)',
        padding: '30px'
    },
    name: {
        color: '#2c3e50',
        fontSize: '28px',
        marginBottom: '30px',
        borderBottom: '3px solid #3498db',
        paddingBottom: '15px'
    },
    details: {
        display: 'flex',
        flexDirection: 'column',
        gap: '15px'
    },
    detailItem: {
        display: 'flex',
        alignItems: 'flex-start',
        gap: '15px',
        padding: '10px 0',
        borderBottom: '1px solid #eee'
    },
    technology: {
        backgroundColor: '#3498db',
        color: 'white',
        padding: '5px 15px',
        borderRadius: '20px',
        fontSize: '14px'
    },
    skillsContainer: {
        display: 'flex',
        flexWrap: 'wrap',
        gap: '8px'
    },
    skill: {
        backgroundColor: '#e9ecef',
        padding: '5px 12px',
        borderRadius: '20px',
        fontSize: '14px',
        color: '#495057'
    }
};

export default TrainerDetail;