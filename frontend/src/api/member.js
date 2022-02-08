import { apiInstance } from './index'

const api = apiInstance();

async function signIn (user, res, err) {
  await api.post('user/signin', JSON.stringify(user)).then(res).catch(err)
}

function userProfile (token, res, err) {
  api.defaults.headers['X-AUTH-TOKEN'] = token;
  api.get(`user/${token.sub}`).then(res).catch(err)
}

function userInfo (user, res, err) {
  api.get(`user/${user}`).then(res).catch(err)
}

export { signIn, userProfile, userInfo }