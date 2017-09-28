import Axios from 'axios';
import msg from 'iview/src/components/message';
import URLS from '../config/urls';

const instance = Axios.create({
  baseURL: URLS.API_URL,
  headers: {
    'Content-Type': 'application/json',
    'Access-Control-Allow-Origin': '*',
    'Access-Control-Allow-Methods': 'GET, POST, PATCH, PUT, OPTIONS',
    'Access-Control-Allow-Headers': 'Content-Type, Accept, Authorization, X-Requested-With, Origin, Accept'
  }
})


instance.interceptors.response.use(function (response) {
    if (response.status == 200) {
        const data = response.data;
        if (data.errCode == 0) {
            return data
        } else {
            console.log(data);
        }
        msg.error(data.errMsg);
        return Promise.reject(data);
    }
    msg.error('服务器响应失败', 3);
    return Promise.reject(response);
}, function (error) {
    msg.error('服务器响应失败', 3);
    return Promise.reject(error);
});

export default instance;
