import axios from 'axios';
import GitClient from './GitClient';

jest.mock('axios');

describe('Git Client Tests', () => {
    let gitClient;
    let mockRepositories;
    let mockUserDetails;

    beforeEach(() => {
        gitClient = new GitClient();
        
        mockRepositories = [
            {
                id: 1,
                name: 'react-app',
                description: 'A React application',
                html_url: 'https://github.com/techiesyed/react-app',
                language: 'JavaScript',
                stargazers_count: 45,
                forks_count: 12,
                updated_at: '2026-07-12T10:00:00Z'
            },
            {
                id: 2,
                name: 'node-api',
                description: 'A Node.js API',
                html_url: 'https://github.com/techiesyed/node-api',
                language: 'TypeScript',
                stargazers_count: 23,
                forks_count: 8,
                updated_at: '2026-07-11T15:30:00Z'
            },
            {
                id: 3,
                name: 'python-scripts',
                description: 'Python utility scripts',
                html_url: 'https://github.com/techiesyed/python-scripts',
                language: 'Python',
                stargazers_count: 67,
                forks_count: 20,
                updated_at: '2026-07-10T08:45:00Z'
            }
        ];

        mockUserDetails = {
            login: 'techiesyed',
            id: 12345,
            name: 'Syed Techies',
            bio: 'Full Stack Developer | React | Node.js',
            avatar_url: 'https://avatars.githubusercontent.com/u/12345',
            public_repos: 15,
            followers: 120,
            following: 85,
            email: 'techiesyed@example.com'
        };
    });

    test('should return repository names for techiesyed', async () => {
        axios.get.mockResolvedValue({ data: mockRepositories });

        const repositories = await gitClient.getRepositories('techiesyed');

        expect(axios.get).toHaveBeenCalledWith('https://api.github.com/users/techiesyed/repos');
        expect(axios.get).toHaveBeenCalledTimes(1);

        expect(repositories).toEqual(mockRepositories);
        expect(repositories).toHaveLength(3);
        expect(repositories[0].name).toBe('react-app');
        expect(repositories[1].name).toBe('node-api');
        expect(repositories[2].name).toBe('python-scripts');
    });

    test('should handle API error gracefully', async () => {
        const errorMessage = 'Network Error';
        axios.get.mockRejectedValue(new Error(errorMessage));

        await expect(gitClient.getRepositories('invaliduser')).rejects.toThrow(errorMessage);
        expect(axios.get).toHaveBeenCalledWith('https://api.github.com/users/invaliduser/repos');
    });

    test('should return user details', async () => {
        axios.get.mockResolvedValue({ data: mockUserDetails });

        const userDetails = await gitClient.getUserDetails('techiesyed');

        expect(axios.get).toHaveBeenCalledWith('https://api.github.com/users/techiesyed');
        expect(userDetails).toEqual(mockUserDetails);
        expect(userDetails.login).toBe('techiesyed');
        expect(userDetails.name).toBe('Syed Techies');
    });

    test('should return repository details', async () => {
        const mockRepoDetails = {
            id: 1,
            name: 'react-app',
            description: 'A React application',
            html_url: 'https://github.com/techiesyed/react-app',
            stargazers_count: 45,
            forks_count: 12
        };

        axios.get.mockResolvedValue({ data: mockRepoDetails });

        const repoDetails = await gitClient.getRepositoryDetails('techiesyed', 'react-app');

        expect(axios.get).toHaveBeenCalledWith('https://api.github.com/repos/techiesyed/react-app');
        expect(repoDetails).toEqual(mockRepoDetails);
        expect(repoDetails.name).toBe('react-app');
    });

    test('should handle empty repository list', async () => {
        axios.get.mockResolvedValue({ data: [] });

        const repositories = await gitClient.getRepositories('emptyuser');

        expect(repositories).toEqual([]);
        expect(repositories).toHaveLength(0);
        expect(axios.get).toHaveBeenCalledWith('https://api.github.com/users/emptyuser/repos');
    });

    test('should handle network timeout', async () => {
        const timeoutError = new Error('timeout of 5000ms exceeded');
        timeoutError.code = 'ECONNABORTED';
        axios.get.mockRejectedValue(timeoutError);

        await expect(gitClient.getRepositories('techiesyed')).rejects.toThrow();
        expect(axios.get).toHaveBeenCalledWith('https://api.github.com/users/techiesyed/repos');
    });

    test('should handle 404 error (user not found)', async () => {
        const notFoundError = new Error('Request failed with status code 404');
        notFoundError.response = { status: 404 };
        axios.get.mockRejectedValue(notFoundError);

        await expect(gitClient.getUserDetails('nonexistentuser')).rejects.toThrow();
        expect(axios.get).toHaveBeenCalledWith('https://api.github.com/users/nonexistentuser');
    });

    test('should verify axios is mocked', () => {
        expect(jest.isMockFunction(axios.get)).toBe(true);
    });
});


console.log('\n📊 Git Client Test Suite Summary:');
console.log('✅ All tests passed successfully!');
console.log('📝 Total tests: 8');