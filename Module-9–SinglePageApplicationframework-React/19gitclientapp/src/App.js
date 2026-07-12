import React, { Component } from 'react';
import './App.css';
import GitClient from './GitClient';

class App extends Component {
    constructor(props) {
        super(props);
        this.state = {
            username: 'techiesyed',
            repositories: [],
            loading: false,
            error: null,
            userInfo: null
        };
        this.gitClient = new GitClient();
    }

    componentDidMount() {
        this.fetchRepositories();
    }

    fetchRepositories = async () => {
        this.setState({ loading: true, error: null });
        try {
            // Fetch user details
            const userInfo = await this.gitClient.getUserDetails(this.state.username);
            // Fetch repositories
            const repos = await this.gitClient.getRepositories(this.state.username);
            
            this.setState({
                repositories: repos,
                userInfo: userInfo,
                loading: false
            });
        } catch (error) {
            this.setState({
                error: error.message || 'Failed to fetch repositories',
                loading: false
            });
        }
    }

    handleUsernameChange = (event) => {
        this.setState({ username: event.target.value });
    }

    handleSearch = (event) => {
        event.preventDefault();
        this.fetchRepositories();
    }

    render() {
        const { username, repositories, loading, error, userInfo } = this.state;

        return (
            <div style={styles.container}>
                <h1 style={styles.header}>🐙 GitHub Repository Viewer</h1>
                
                <form onSubmit={this.handleSearch} style={styles.searchForm}>
                    <input
                        type="text"
                        value={username}
                        onChange={this.handleUsernameChange}
                        placeholder="Enter GitHub username"
                        style={styles.searchInput}
                    />
                    <button type="submit" style={styles.searchButton}>
                        🔍 Search
                    </button>
                </form>

                {loading && (
                    <div style={styles.loadingContainer}>
                        <div style={styles.spinner}></div>
                        <p>Loading repositories...</p>
                    </div>
                )}

                {error && (
                    <div style={styles.errorContainer}>
                        <p style={styles.errorText}>❌ {error}</p>
                        <p style={styles.errorHint}>Please try again with a valid username</p>
                    </div>
                )}

                {!loading && !error && userInfo && (
                    <div style={styles.userInfo}>
                        <img 
                            src={userInfo.avatar_url} 
                            alt={userInfo.login}
                            style={styles.avatar}
                        />
                        <div style={styles.userDetails}>
                            <h2>{userInfo.name || userInfo.login}</h2>
                            <p>{userInfo.bio}</p>
                            <div style={styles.userStats}>
                                <span>📁 {userInfo.public_repos} repos</span>
                                <span>👥 {userInfo.followers} followers</span>
                                <span>👤 {userInfo.following} following</span>
                            </div>
                        </div>
                    </div>
                )}

                {!loading && !error && repositories.length > 0 && (
                    <div style={styles.repoContainer}>
                        <h2 style={styles.repoHeader}>📚 Repositories ({repositories.length})</h2>
                        <div style={styles.repoList}>
                            {repositories.map(repo => (
                                <div key={repo.id} style={styles.repoCard}>
                                    <div style={styles.repoHeader}>
                                        <h3 style={styles.repoName}>
                                            <a href={repo.html_url} target="_blank" rel="noopener noreferrer">
                                                {repo.name}
                                            </a>
                                        </h3>
                                        <span style={styles.repoLanguage}>
                                            {repo.language || '🌐 Multiple'}
                                        </span>
                                    </div>
                                    <p style={styles.repoDescription}>
                                        {repo.description || 'No description available'}
                                    </p>
                                    <div style={styles.repoStats}>
                                        <span>⭐ {repo.stargazers_count}</span>
                                        <span>🍴 {repo.forks_count}</span>
                                        <span>📅 {new Date(repo.updated_at).toLocaleDateString()}</span>
                                    </div>
                                </div>
                            ))}
                        </div>
                    </div>
                )}
            </div>
        );
    }
}

// Add keyframes for spinner animation
const styleSheet = document.createElement("style");
styleSheet.textContent = `
    @keyframes spin {
        0% { transform: rotate(0deg); }
        100% { transform: rotate(360deg); }
    }
`;
document.head.appendChild(styleSheet);

const styles = {
    container: {
        maxWidth: '1000px',
        margin: '0 auto',
        padding: '20px',
        fontFamily: 'Arial, sans-serif'
    },
    header: {
        textAlign: 'center',
        color: '#2c3e50',
        fontSize: '36px',
        marginBottom: '30px',
        borderBottom: '3px solid #3498db',
        paddingBottom: '15px'
    },
    searchForm: {
        display: 'flex',
        gap: '10px',
        marginBottom: '30px',
        justifyContent: 'center'
    },
    searchInput: {
        flex: '1',
        maxWidth: '400px',
        padding: '12px 20px',
        fontSize: '16px',
        border: '2px solid #ced4da',
        borderRadius: '8px',
        outline: 'none',
        transition: 'border-color 0.3s'
    },
    searchButton: {
        backgroundColor: '#3498db',
        color: 'white',
        border: 'none',
        padding: '12px 30px',
        fontSize: '16px',
        fontWeight: '600',
        borderRadius: '8px',
        cursor: 'pointer',
        transition: 'all 0.3s'
    },
    loadingContainer: {
        textAlign: 'center',
        padding: '40px'
    },
    spinner: {
        width: '50px',
        height: '50px',
        margin: '0 auto 20px',
        border: '5px solid #e9ecef',
        borderTop: '5px solid #3498db',
        borderRadius: '50%',
        animation: 'spin 1s linear infinite'
    },
    errorContainer: {
        backgroundColor: '#f8d7da',
        padding: '20px',
        borderRadius: '8px',
        textAlign: 'center',
        marginBottom: '20px'
    },
    errorText: {
        color: '#dc3545',
        fontSize: '18px',
        fontWeight: '600',
        margin: '0'
    },
    errorHint: {
        color: '#721c24',
        margin: '5px 0 0 0'
    },
    userInfo: {
        display: 'flex',
        gap: '20px',
        padding: '20px',
        backgroundColor: '#f8f9fa',
        borderRadius: '10px',
        marginBottom: '30px',
        alignItems: 'center',
        flexWrap: 'wrap'
    },
    avatar: {
        width: '100px',
        height: '100px',
        borderRadius: '50%',
        border: '3px solid #3498db'
    },
    userDetails: {
        flex: '1'
    },
    userStats: {
        display: 'flex',
        gap: '20px',
        marginTop: '10px'
    },
    repoContainer: {
        marginTop: '20px'
    },
    repoHeader: {
        color: '#2c3e50',
        fontSize: '22px',
        marginBottom: '15px'
    },
    repoList: {
        display: 'grid',
        gridTemplateColumns: 'repeat(auto-fit, minmax(300px, 1fr))',
        gap: '20px'
    },
    repoCard: {
        backgroundColor: 'white',
        padding: '20px',
        borderRadius: '10px',
        boxShadow: '0 2px 10px rgba(0,0,0,0.1)',
        borderLeft: '4px solid #3498db',
        transition: 'transform 0.3s'
    },
    repoName: {
        margin: '0',
        fontSize: '18px'
    },
    repoName: {
        margin: '0',
        fontSize: '18px'
    },
    repoLanguage: {
        backgroundColor: '#e9ecef',
        padding: '3px 12px',
        borderRadius: '20px',
        fontSize: '12px',
        color: '#495057'
    },
    repoDescription: {
        color: '#6c757d',
        fontSize: '14px',
        margin: '10px 0'
    },
    repoStats: {
        display: 'flex',
        gap: '15px',
        fontSize: '14px',
        color: '#6c757d'
    }
};

export default App;