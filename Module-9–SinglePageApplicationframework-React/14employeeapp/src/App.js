import React, { Component } from 'react';
import './App.css';
import EmployeesList from './Components/EmployeesList';
import ThemeContext from './ThemeContext';

class App extends Component {
    constructor(props) {
        super(props);
        this.state = {
            theme: 'light',
            employees: [
                {
                    id: 1,
                    name: 'John Smith',
                    email: 'john.smith@company.com',
                    department: 'Engineering',
                    position: 'Senior Developer',
                    salary: 85000
                },
                {
                    id: 2,
                    name: 'Sarah Johnson',
                    email: 'sarah.johnson@company.com',
                    department: 'Marketing',
                    position: 'Marketing Manager',
                    salary: 72000
                },
                {
                    id: 3,
                    name: 'Michael Brown',
                    email: 'michael.brown@company.com',
                    department: 'Sales',
                    position: 'Sales Executive',
                    salary: 65000
                },
                {
                    id: 4,
                    name: 'Emily Davis',
                    email: 'emily.davis@company.com',
                    department: 'Engineering',
                    position: 'Team Lead',
                    salary: 92000
                },
                {
                    id: 5,
                    name: 'David Wilson',
                    email: 'david.wilson@company.com',
                    department: 'Human Resources',
                    position: 'HR Specialist',
                    salary: 58000
                },
                {
                    id: 6,
                    name: 'Lisa Anderson',
                    email: 'lisa.anderson@company.com',
                    department: 'Engineering',
                    position: 'DevOps Engineer',
                    salary: 78000
                }
            ]
        };
        this.toggleTheme = this.toggleTheme.bind(this);
    }

    toggleTheme() {
        this.setState((prevState) => ({
            theme: prevState.theme === 'light' ? 'dark' : 'light'
        }));
    }

    render() {
        const appStyles = {
            light: {
                backgroundColor: '#ffffff',
                color: '#2c3e50'
            },
            dark: {
                backgroundColor: '#2c2c2c',
                color: '#f8f9fa'
            }
        };

        const currentStyle = this.state.theme === 'dark' ? appStyles.dark : appStyles.light;

        return (
            // Step 6a: Import ThemeContext
            // Step 6b: Define the theme context provider
            // Step 6c: Assign the value for the theme provider from state
            <ThemeContext.Provider value={this.state.theme}>
                <div style={{
                    ...styles.app,
                    ...currentStyle
                }}>
                    <header style={styles.header}>
                        <div style={styles.headerContent}>
                            <h1 style={styles.title}>🏢 Employee Management System</h1>
                            <div style={styles.themeToggle}>
                                <span style={styles.themeLabel}>
                                    {this.state.theme === 'light' ? '☀️' : '🌙'}
                                </span>
                                <button 
                                    onClick={this.toggleTheme}
                                    style={styles.toggleButton}
                                >
                                    Switch to {this.state.theme === 'light' ? 'Dark' : 'Light'} Theme
                                </button>
                            </div>
                        </div>
                    </header>

                    <main style={styles.main}>
                        {/* Step 6d: Remove theme prop from EmployeeList */}
                        <EmployeesList employees={this.state.employees} />
                    </main>

                    <footer style={styles.footer}>
                        <p>© 2026 Employee Management System | Powered by React Context API</p>
                        <p style={styles.themeStatus}>
                            Current Theme: <strong>{this.state.theme}</strong>
                        </p>
                    </footer>
                </div>
            </ThemeContext.Provider>
        );
    }
}

const styles = {
    app: {
        minHeight: '100vh',
        transition: 'all 0.3s ease'
    },
    header: {
        backgroundColor: '#007bff',
        padding: '20px 0',
        boxShadow: '0 2px 10px rgba(0,0,0,0.1)'
    },
    headerContent: {
        maxWidth: '1200px',
        margin: '0 auto',
        padding: '0 20px',
        display: 'flex',
        justifyContent: 'space-between',
        alignItems: 'center',
        flexWrap: 'wrap'
    },
    title: {
        color: 'white',
        margin: '0',
        fontSize: '28px'
    },
    themeToggle: {
        display: 'flex',
        alignItems: 'center',
        gap: '10px'
    },
    themeLabel: {
        fontSize: '24px'
    },
    toggleButton: {
        backgroundColor: 'rgba(255,255,255,0.2)',
        color: 'white',
        border: '2px solid white',
        padding: '8px 20px',
        borderRadius: '20px',
        cursor: 'pointer',
        fontSize: '14px',
        fontWeight: '600',
        transition: 'all 0.3s ease'
    },
    main: {
        maxWidth: '1200px',
        margin: '0 auto',
        padding: '20px'
    },
    footer: {
        textAlign: 'center',
        padding: '20px',
        backgroundColor: '#f8f9fa',
        borderTop: '1px solid #dee2e6',
        marginTop: '20px'
    },
    themeStatus: {
        marginTop: '5px',
        fontSize: '14px',
        color: '#6c757d'
    }
};

export default App;