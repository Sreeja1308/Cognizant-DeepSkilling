import axios from 'axios';

class GitClient {
    // Method to fetch repositories for a given username
    async getRepositories(username) {
        try {
            const response = await axios.get(`https://api.github.com/users/${username}/repos`);
            return response.data;
        } catch (error) {
            console.error('Error fetching repositories:', error);
            throw error;
        }
    }

    // Method to fetch user details
    async getUserDetails(username) {
        try {
            const response = await axios.get(`https://api.github.com/users/${username}`);
            return response.data;
        } catch (error) {
            console.error('Error fetching user details:', error);
            throw error;
        }
    }

    // Method to fetch repository details
    async getRepositoryDetails(username, repoName) {
        try {
            const response = await axios.get(`https://api.github.com/repos/${username}/${repoName}`);
            return response.data;
        } catch (error) {
            console.error('Error fetching repository details:', error);
            throw error;
        }
    }
}

export default GitClient;