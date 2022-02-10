import { apiInstance } from './index'

const api = apiInstance();

// 링크박스 관련 코드
function createLinkbox (boxdata, res, err) {
  api.post('linkbox/create', JSON.stringify(boxdata)).then(res).catch(err)
}

function listLinkbox (res, err) {
  api.get('linkbox/list').then(res).catch(err)
}

function listUserLinkbox (userid, res, err) {
  api.get(`linkbox/list/${userid}`).then(res).catch(err)
}

function viewLinkbox (boxdata, res, err) {
  api.get(`linkbox/viewcount/${boxdata.boxid}`).then(res).catch(err)
}

function updateLinkbox (boxdata, res, err) {
  api.post('linkbox', JSON.stringify(boxdata)).then(res).catch(err)
}

function deleteLinkbox (boxdata, res, err) {
  api.delete(`linkbox/${boxdata.boxid}`).then(res).catch(err)
}

function addLinkboxInterest (boxdata, res, err) {
  api.post('linkbox/interest', JSON.stringify(boxdata)).then(res).catch(err)
}

function deleteLinkboxInterest (boxdata, res, err) {
  api.delete('linkbox/interest', JSON.stringify(boxdata)).then(res).catch(err)
}

// 링크박스 정보 불러오기
function getLinkboxInfo (boxid,  res, err) {
  api.get(`linkbox/one/${boxid}`).then(res).catch(err)
}

function getLinkboxInterest (boxid,  res, err) {
  api.get(`linkbox/interest/${boxid}`).then(res).catch(err)
}

// 링크박스 내 링크 관련 코드
function createLink (linkdata, res, err) {
  api.post('linkdetail', JSON.stringify(linkdata)).then(res).catch(err)
}

function listLink (boxid, res, err) {
  api.get(`linkdetail/${boxid}`).then(res).catch(err)
}

function updateLink (linkdata, res, err) {
  api.put('linkdetail', JSON.stringify(linkdata)).then(res).catch(err)
}

function deleteLink (linkid, res, err) {
  api.delete(`linkdetail/${linkid}`).then(res).catch(err)
}

export {
  createLinkbox, listLinkbox, listUserLinkbox, viewLinkbox,
  updateLinkbox, deleteLinkbox,
  addLinkboxInterest, deleteLinkboxInterest,
  getLinkboxInfo, getLinkboxInterest,
  createLink, listLink, updateLink, deleteLink
}