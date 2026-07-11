import React, { Component } from 'react';

class ListofPlayers extends Component {
    constructor(props) {
        super(props);
        this.state = {
            players: [
                { name: 'Virat Kohli', score: 89 },
                { name: 'Rohit Sharma', score: 95 },
                { name: 'MS Dhoni', score: 72 },
                { name: 'KL Rahul', score: 45 },
                { name: 'Suryakumar Yadav', score: 82 },
                { name: 'Rishabh Pant', score: 38 },
                { name: 'Hardik Pandya', score: 65 },
                { name: 'Jasprit Bumrah', score: 15 },
                { name: 'Ravindra Jadeja', score: 55 },
                { name: 'Mohammed Shami', score: 30 },
                { name: 'Yuzvendra Chahal', score: 20 }
            ]
        };
    }

    render() {
        // Using map() to display all players
        const allPlayers = this.state.players.map((player, index) => (
            <div key={index} style={styles.playerCard}>
                <span style={styles.playerName}>{player.name}</span>
                <span style={styles.playerScore}>{player.score}</span>
            </div>
        ));

        // Using arrow functions with filter() to get players with score below 70
        const lowScorers = this.state.players
            .filter(player => player.score < 70)
            .map((player, index) => (
                <div key={index} style={styles.lowScoreCard}>
                    <span style={styles.playerName}>{player.name}</span>
                    <span style={styles.lowScore}>{player.score}</span>
                </div>
            ));

        return (
            <div style={styles.container}>
                <h2 style={styles.header}>🏏 Cricket Players List</h2>
                
                <div style={styles.section}>
                    <h3 style={styles.subHeader}>All Players (11 Players)</h3>
                    <div style={styles.listContainer}>
                        {allPlayers}
                    </div>
                </div>

                <div style={styles.section}>
                    <h3 style={styles.subHeader}>Players with Score Below 70</h3>
                    {lowScorers.length > 0 ? (
                        <div style={styles.listContainer}>
                            {lowScorers}
                        </div>
                    ) : (
                        <p style={styles.noData}>No players with score below 70</p>
                    )}
                </div>
            </div>
        );
    }
}

const styles = {
    container: {
        maxWidth: '600px',
        margin: '20px auto',
        padding: '20px',
        backgroundColor: '#f8f9fa',
        borderRadius: '15px',
        boxShadow: '0 4px 15px rgba(0,0,0,0.1)'
    },
    header: {
        textAlign: 'center',
        color: '#2c3e50',
        borderBottom: '3px solid #3498db',
        paddingBottom: '10px',
        marginBottom: '25px'
    },
    section: {
        marginBottom: '30px'
    },
    subHeader: {
        color: '#495057',
        fontSize: '18px',
        marginBottom: '15px',
        paddingLeft: '10px',
        borderLeft: '4px solid #3498db'
    },
    listContainer: {
        backgroundColor: 'white',
        borderRadius: '10px',
        padding: '10px',
        boxShadow: '0 2px 5px rgba(0,0,0,0.05)'
    },
    playerCard: {
        display: 'flex',
        justifyContent: 'space-between',
        padding: '10px 15px',
        borderBottom: '1px solid #e9ecef',
        transition: 'background-color 0.3s'
    },
    lowScoreCard: {
        display: 'flex',
        justifyContent: 'space-between',
        padding: '10px 15px',
        borderBottom: '1px solid #e9ecef',
        backgroundColor: '#fff3cd',
        transition: 'background-color 0.3s'
    },
    playerName: {
        fontSize: '16px',
        fontWeight: '500',
        color: '#2c3e50'
    },
    playerScore: {
        fontSize: '16px',
        fontWeight: '600',
        color: '#28a745'
    },
    lowScore: {
        fontSize: '16px',
        fontWeight: '600',
        color: '#dc3545'
    },
    noData: {
        textAlign: 'center',
        color: '#6c757d',
        padding: '20px'
    }
};

export default ListofPlayers;