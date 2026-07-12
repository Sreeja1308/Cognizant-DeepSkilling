import React, { useContext } from 'react';
import ThemeContext from '../ThemeContext';

function EmployeeCard({ employee }) {
    // Using useContext hook to get theme value
    const theme = useContext(ThemeContext);

    const cardStyles = {
        light: {
            container: {
                backgroundColor: '#ffffff',
                color: '#2c3e50',
                border: '1px solid #dee2e6',
                boxShadow: '0 2px 8px rgba(0,0,0,0.1)'
            },
            button: {
                backgroundColor: '#007bff',
                color: 'white'
            },
            buttonHover: {
                backgroundColor: '#0056b3'
            }
        },
        dark: {
            container: {
                backgroundColor: '#343a40',
                color: '#f8f9fa',
                border: '1px solid #495057',
                boxShadow: '0 2px 8px rgba(0,0,0,0.3)'
            },
            button: {
                backgroundColor: '#6c757d',
                color: 'white'
            },
            buttonHover: {
                backgroundColor: '#5a6268'
            }
        }
    };

    const currentTheme = theme === 'dark' ? cardStyles.dark : cardStyles.light;

    return (
        <div style={{
            ...styles.card,
            ...currentTheme.container
        }}>
            <div style={styles.cardHeader}>
                <h3 style={styles.name}>{employee.name}</h3>
                <span style={{
                    ...styles.badge,
                    backgroundColor: employee.department === 'Engineering' ? '#28a745' :
                                   employee.department === 'Marketing' ? '#ffc107' :
                                   employee.department === 'Sales' ? '#17a2b8' :
                                   '#dc3545'
                }}>
                    {employee.department}
                </span>
            </div>
            <div style={styles.details}>
                <p><strong>ID:</strong> {employee.id}</p>
                <p><strong>Email:</strong> {employee.email}</p>
                <p><strong>Position:</strong> {employee.position}</p>
                <p><strong>Salary:</strong> ${employee.salary.toLocaleString()}</p>
            </div>
            <div style={styles.buttonContainer}>
                <button 
                    style={{
                        ...styles.button,
                        ...currentTheme.button
                    }}
                    onMouseEnter={(e) => {
                        e.target.style.backgroundColor = currentTheme.buttonHover.backgroundColor;
                    }}
                    onMouseLeave={(e) => {
                        e.target.style.backgroundColor = currentTheme.button.backgroundColor;
                    }}
                >
                    View Profile
                </button>
                <button 
                    style={{
                        ...styles.button,
                        ...currentTheme.button
                    }}
                    onMouseEnter={(e) => {
                        e.target.style.backgroundColor = currentTheme.buttonHover.backgroundColor;
                    }}
                    onMouseLeave={(e) => {
                        e.target.style.backgroundColor = currentTheme.button.backgroundColor;
                    }}
                >
                    Contact
                </button>
            </div>
            <div style={styles.themeIndicator}>
                Theme: <span style={{ fontWeight: 'bold' }}>{theme}</span>
            </div>
        </div>
    );
}

const styles = {
    card: {
        padding: '20px',
        borderRadius: '10px',
        marginBottom: '20px',
        transition: 'all 0.3s ease'
    },
    cardHeader: {
        display: 'flex',
        justifyContent: 'space-between',
        alignItems: 'center',
        marginBottom: '15px',
        borderBottom: '1px solid #dee2e6',
        paddingBottom: '10px'
    },
    name: {
        margin: '0',
        fontSize: '20px',
        fontWeight: '600'
    },
    badge: {
        padding: '5px 12px',
        borderRadius: '20px',
        color: 'white',
        fontSize: '12px',
        fontWeight: '600'
    },
    details: {
        marginBottom: '15px'
    },
    buttonContainer: {
        display: 'flex',
        gap: '10px',
        marginTop: '10px'
    },
    button: {
        padding: '8px 20px',
        border: 'none',
        borderRadius: '5px',
        cursor: 'pointer',
        fontSize: '14px',
        fontWeight: '500',
        transition: 'all 0.3s ease'
    },
    themeIndicator: {
        marginTop: '15px',
        paddingTop: '10px',
        borderTop: '1px solid #dee2e6',
        fontSize: '12px',
        opacity: '0.7'
    }
};

export default EmployeeCard;