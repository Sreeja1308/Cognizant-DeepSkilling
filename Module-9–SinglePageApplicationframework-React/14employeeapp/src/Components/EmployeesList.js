import React from 'react';
import EmployeeCard from './EmployeeCard';

function EmployeesList({ employees }) {
    return (
        <div style={styles.container}>
            <h2 style={styles.header}>👥 Employee List</h2>
            <p style={styles.subHeader}>Total Employees: {employees.length}</p>
            <div style={styles.grid}>
                {employees.map(employee => (
                    <EmployeeCard 
                        key={employee.id} 
                        employee={employee}
                    />
                ))}
            </div>
        </div>
    );
}

const styles = {
    container: {
        padding: '20px',
        backgroundColor: '#f8f9fa',
        borderRadius: '10px',
        minHeight: '400px'
    },
    header: {
        color: '#2c3e50',
        fontSize: '28px',
        marginBottom: '5px'
    },
    subHeader: {
        color: '#6c757d',
        marginBottom: '20px'
    },
    grid: {
        display: 'grid',
        gridTemplateColumns: 'repeat(auto-fit, minmax(350px, 1fr))',
        gap: '20px'
    }
};

export default EmployeesList;