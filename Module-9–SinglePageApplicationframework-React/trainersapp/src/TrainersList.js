import React from 'react';
import { Link } from 'react-router-dom';

function TrainersList({ trainers }) {
    return (
        <div style={styles.container}>
            <h2 style={styles.header}>Trainers List</h2>
            <ul style={styles.list}>
                {trainers.map(trainer => (
                    <li key={trainer.trainerId} style={styles.listItem}>
                        <Link 
                            to={`/trainers/${trainer.trainerId}`} 
                            style={styles.link}
                        >
                            {trainer.name}
                        </Link>
                        <span style={styles.technology}>
                            {trainer.technology}
                        </span>
                    </li>
                ))}
            </ul>
        </div>
    );
}

const styles = {
    container: {
        padding: '30px',
        maxWidth: '800px',
        margin: '0 auto'
    },
    header: {
        color: '#2c3e50',
        borderBottom: '2px solid #3498db',
        paddingBottom: '10px',
        marginBottom: '30px'
    },
    list: {
        listStyle: 'none',
        padding: '0',
        margin: '0'
    },
    listItem: {
        padding: '15px',
        marginBottom: '10px',
        backgroundColor: '#f8f9fa',
        borderRadius: '8px',
        display: 'flex',
        justifyContent: 'space-between',
        alignItems: 'center',
        transition: 'background-color 0.3s',
        cursor: 'pointer'
    },
    link: {
        fontSize: '18px',
        color: '#3498db',
        textDecoration: 'none',
        fontWeight: '500'
    },
    technology: {
        backgroundColor: '#e9ecef',
        padding: '5px 15px',
        borderRadius: '20px',
        fontSize: '14px',
        color: '#495057'
    }
};

export default TrainersList;