import { apiInstance } from './index'

const api = apiInstance();

async function signup(user, success, fail) {
  await api
    .post(`user/signup`, JSON.stringify(user))
    .then(success)
    .catch(fail);
}

export { signup }