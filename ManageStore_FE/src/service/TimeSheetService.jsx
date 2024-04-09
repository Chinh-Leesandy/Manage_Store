import axios from 'axios';

const API_URL = 'http://localhost:8080';

const TimeSheetService = {
    getEmployeeTimeKeeping: async () => {
      try {
        const response = await axios.get(`${API_URL}/employeeTimeKeeping`);
        return response.data;
      } catch (error) {
        throw new Error(error.message);
      }
    },
  
    getTKTimeSheet: async (month) => {
      try {
        const response = await axios.get(`${API_URL}/TKTimeSheet/${month}`);
        return response.data;
      } catch (error) {
        throw new Error(error.message);
      }
    }
  };
  
  export default TimeSheetService;