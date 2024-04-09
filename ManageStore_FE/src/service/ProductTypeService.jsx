import axios from 'axios';

const API_URL = 'http://localhost:8080';

const ProductTypeService = {
    getProductType: async () => {
        try {
          const response = await axios.get(`${API_URL}/productType`);
          return response.data;
        } catch (error) {
          throw Error('Error while fetching productTypes');
        }
      },
    
      getProductTypeId: async (id) => {
        try {
          const response = await axios.get(`${API_URL}/productType/${id}`);
          return response.data;
        } catch (error) {
          throw Error('Error while fetching productType by ID');
        }
      },
    
      addProductType: async (productType) => {
        try {
          const response = await axios.post(`${API_URL}/addProductType`, productType);
          return response.data;
        } catch (error) {
          throw Error('Error while adding productType');
        }
      },
    
      updateProductType: async (productType) => {
        try {
          const response = await axios.put(`${API_URL}/updateProductType`, productType);
          return response.data;
        } catch (error) {
          throw Error('Error while updating productType');
        }
      },
    
      deleteProductType: async (id) => {
        try {
          const response = await axios.delete(`${API_URL}/deleteProductType/${id}`);
          return response.data;
        } catch (error) {
          throw Error('Error while deleting productType');
        }
      }
}
export default ProductTypeService;