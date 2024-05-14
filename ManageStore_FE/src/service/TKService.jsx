import axios from "axios";
const API_URL = "http://localhost:8092/api/v1/statistical";

export const TKService = {
    getTkByTime: async () => {
        try {
            const response = await axios.get(`${API_URL}/by-time`);
            return response.data;
        } catch (error) {
            throw Error('Error while fetching tk by time');
        }
    }, 
    getTkByCustomer: async () => {
        try {
            const response = await axios.get(`${API_URL}/by-customer`);
            return response.data;
        } catch (error) {
            throw Error('Error while fetching tk by customer');
        }
    }
}