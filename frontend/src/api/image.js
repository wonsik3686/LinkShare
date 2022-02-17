import { apiInstanceMulti } from './index'

const api = apiInstanceMulti();

async function putImg (formData, res, err) {
  await api.put('user/img', formData).then(res).catch(err)
}

async function getImgNickname (nickname, res, err) {
  await api.get(`user/img/nickname/${nickname}`).then(res).catch(err)
}

async function getImgUid (uid, res, err) {
  await api.get(`user/img/uid/${uid}`).then(res).catch(err)
}

async function getImgEmail (email, res, err) {
  await api.get(`user/img/email/${email}`).then(res).catch(err)
}

export {
  putImg, getImgNickname, getImgUid, getImgEmail,
}