import React from 'react';
import './App.css';
import { BrowserRouter, Routes, Route, Link } from 'react-router-dom';
import Home from './Home';
import TrainersList from './TrainersList';
import TrainerDetail from './TrainerDetail';
import trainersData from './TrainersMock';

function App() {
    return (
        <BrowserRouter>
            <div style={styles.app}>
                <nav style={styles.nav}>
                    <ul style={styles.navList}>
                        <li style={styles.navItem}>
                            <Link to="/" style={styles.navLink}>
                                Home
                            </Link>
                        </li>
                        <li style={styles.navItem}>
                            <Link to="/trainers" style={styles.navLink}>
                                Trainers
                            </Link>
                        </li>
                    </ul>
                </nav>

                <main style={styles.main}>
                    <Routes>
                        <Route path="/" element={<Home />} />
                        <Route path="/trainers" element={<TrainersList trainers={trainersData} />} />
                        <Route path="/trainers/:id" element={<TrainerDetail trainers={trainersData} />} />
                    </Routes>
                </main>
            </div>
        </BrowserRouter>
    );
}

const styles = {
    app: {
        minHeight: '100vh',
        backgroundColor: '#f0f2f5'
    },
    nav: {
        backgroundColor: '#2c3e50',
        padding: '15px 30px',
        boxShadow: '0 2px 4px rgba(0,0,0,0.1)'
    },
    navList: {
        listStyle: 'none',
        padding: '0',
        margin: '0',
        display: 'flex',
        gap: '30px'
    },
    navItem: {
        margin: '0'
    },
    navLink: {
        color: 'white',
        textDecoration: 'none',
        fontSize: '18px',
        fontWeight: '500',
        padding: '8px 16px',
        borderRadius: '5px',
        transition: 'background-color 0.3s'
    },
    main: {
        padding: '20px'
    }
};

export default App;