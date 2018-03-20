import Axios from 'axios';
import URLS from '../config/urls';

const instance = Axios.create({
  baseURL: URLS.API_URL
})


instance.interceptors.response.use(function (response) {
    if (response.status == 200) {
        const data = response.data;
        return data
    }
    this.callDialog('服务器响应失败');
    return Promise.reject(response);
}, function (error) {
    this.callDialog('服务器响应失败');
    return Promise.reject(error);
});

export default instance;
