import Axios from 'axios';
import msg from 'iview/src/components/message';
import URLS from '../config/urls';

const instance = Axios.create({
  baseURL: URLS.API_URL
})


instance.interceptors.response.use(function (response) {
    if (response.status == 200) {
        const data = response.data;
        return data
    }
    msg.error('服务器响应失败', 3);
    return Promise.reject(response);
}, function (error) {
    msg.error('服务器响应失败', 3);
    return Promise.reject(error);
});

export default instance;
