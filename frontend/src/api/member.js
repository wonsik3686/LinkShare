import { apiInstance } from './index'

const api = apiInstance();

async function signup (userdata, res, err) {
  await api.post('user/signup', JSON.stringify(userdata)).then(res).catch(err)
}

async function signIn (userdata, res, err) {
  await api.post('user/signin', JSON.stringify(userdata)).then(res).catch(err)
}

async function userProfile (token, res, err) {
  api.defaults.headers['X-AUTH-TOKEN'] = token;
  await api.get(`user/${token.sub}`).then(res).catch(err)
}

async function updateProfile (userdata, res, err) {
  await api.put('user', JSON.stringify(userdata)).then(res).catch(err)
}

async function userInfo (email, res, err) {
  await api.get(`user/${email}`).then(res).catch(err)
}

async function getUserUid (userid, res, err) {
  await api.get(`user?uid=${userid}`).then(res).catch(err)
}

// 관심사 관련 코드
function createUserInterest (interestdata, res, err) {
  api.post('user/interest', JSON.stringify(interestdata)).then(res).catch(err)
}

function getUserInterest (userid, userdata, res, err) {
  api.get(`user/interest/${userid}`, JSON.stringify(userdata)).then(res).catch(err)
}

function deleteUserInterest (userid, interest, res, err) {
  api.delete(`user/interest?uid=${userid}&interest=${interest}`).then(res).catch(err)
}

export { signup, signIn, userProfile, updateProfile, userInfo, getUserUid }
export { createUserInterest, getUserInterest, deleteUserInterest }