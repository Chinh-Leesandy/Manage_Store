import axios from "axios"

const API_URL = "http://localhost:8090/api/v1/invoice"

export const TKBetweenTime =  {
    getTkBetweenTime: async (start, end) => {
        try {
            const response = await axios.get(`${API_URL}/get-invoices-between`, {
                params: {
                  start: start,
                  end: end,
                },
            });
            return response.data;
        } catch (error) {
            throw Error('Error while fetching tk by between time');
        }
    }

}
