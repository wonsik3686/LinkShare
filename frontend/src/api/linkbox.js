import { apiInstance } from './index'

const api = apiInstance();

// 링크박스 관련 코드
async function createLinkbox (boxdata, res, err) {
  await api.post('linkbox/create', JSON.stringify(boxdata)).then(res).catch(err)
}

async function listLinkbox (res, err) {
  await api.get('linkbox/list').then(res).catch(err)
}

async function listPopLinkbox (res, err) {
  await api.get('linkbox/popular').then(res).catch(err)
}

async function listInterestLinkbox (userid, res, err) {
  await api.get(`linkbox/interests/${userid}`).then(res).catch(err)
}

async function listUserLinkbox (userid, res, err) {
  await api.get(`linkbox/list/${userid}`).then(res).catch(err)
}

async function viewLinkbox (boxid, res, err) {
  await api.get(`linkbox/viewcount/${boxid}`).then(res).catch(err)
}

async function updateLinkbox (boxdata, res, err) {
  await api.put('linkbox', JSON.stringify(boxdata)).then(res).catch(err)
}

async function deleteLinkbox (boxid, res, err) {
  await api.delete(`linkbox/${boxid}`).then(res).catch(err)
}

// 링크박스 관심사 관련 코드
async function addLinkboxInterest (boxdata, res, err) {
  await api.post('linkbox/interest', JSON.stringify(boxdata)).then(res).catch(err)
}

async function getInterestList (boxid, res, err) {
  await api.get(`linkbox/interest/${boxid}`).then(res).catch(err)
}

async function deleteLinkboxInterest (boxid, interest, res, err) {
  await api.delete(`linkbox/interest?boxid=${boxid}&interest=${interest}`).then(res).catch(err)
}

// 링크박스 정보 불러오기
async function getLinkboxInfo (boxid,  res, err) {
  await api.get(`linkbox/one/${boxid}`).then(res).catch(err)
}

async function getLinkboxInterest (boxid,  res, err) {
  await api.get(`linkbox/interest/${boxid}`).then(res).catch(err)
}

// 댓글 관련 코드
async function createLinkboxComment (boxdata, res, err) {
  await api.post('linkbox/comment', JSON.stringify(boxdata)).then(res).catch(err)
}

async function deleteLinkboxComment (commentid, res, err) {
  await api.delete(`linkbox/comment/${commentid}`).then(res).catch(err)
}

async function updateLinkboxComment (boxdata, res, err) {
  await api.put('linkbox/comment', JSON.stringify(boxdata)).then(res).catch(err)
}

async function searchLinkBoxCommentByBoxId (boxid, res, err) {
  await api.get(`linkbox/comment/${boxid}`).then(res).catch(err)
}

// 링크박스 좋아요 관련 코드
async function likeLinkbox (userboxdata, res, err) {
  await api.post('linkbox/like', JSON.stringify(userboxdata)).then(res).catch(err)
}

async function unlikeLinkbox (userid, boxid, res, err) {
  await api.delete(`linkbox/like?uid=${userid}&boxid=${boxid}`).then(res).catch(err)
}

async function getLikeList(boxid, res, err) {
  await api.get(`linkbox/like/${boxid}`).then(res).catch(err)
}

async function getUserLike(boxid, userid, res, err) {
  await api.get(`linkbox/like?boxid=${boxid}&uid=${userid}`).then(res).catch(err)
}

// 링크박스 스크랩 관련 코드
async function createScrap (userboxdata, res, err) {
  await api.post('linkbox/scrap', JSON.stringify(userboxdata)).then(res).catch(err)
}

async function deleteScrap (userid, boxid, res, err) {
  await api.delete(`linkbox/scrap?uid=${userid}&boxid=${boxid}`).then(res).catch(err)
}

async function getScrapBoxid (boxid, res, err) {
  await api.get(`linkbox/scrap/box/${boxid}`).then(res).catch(err)
}

async function getScrapUid(userid, res, err) {
  await api.get(`linkbox/scrap/user/${userid}`).then(res).catch(err)
}

async function getUserScrap(userid, boxid, res, err) {
  await api.get(`linkbox/scrap?uid=${userid}&boxid=${boxid}`).then(res).catch(err)
}

// 링크박스 내 링크 관련 코드
async function createLink (linkdata, res, err) {
  await api.post('linkdetail', JSON.stringify(linkdata)).then(res).catch(err)
}

async function listLink (boxid, res, err) {
  await api.get(`linkdetail/${boxid}`).then(res).catch(err)
}

async function updateLink (linkdata, res, err) {
  await api.put('linkdetail', JSON.stringify(linkdata)).then(res).catch(err)
}

async function deleteLink (linkid, res, err) {
  await api.delete(`linkdetail/${linkid}`).then(res).catch(err)
}

export {
  createLinkbox, listLinkbox, listUserLinkbox, viewLinkbox,
  updateLinkbox, deleteLinkbox,

  listPopLinkbox, listInterestLinkbox, 

  addLinkboxInterest, deleteLinkboxInterest, getInterestList,
  getLinkboxInfo, getLinkboxInterest,

  createLinkboxComment, deleteLinkboxComment, updateLinkboxComment, searchLinkBoxCommentByBoxId,

  likeLinkbox, unlikeLinkbox, getLikeList, getUserLike,

  createScrap, deleteScrap, getScrapBoxid, getScrapUid, getUserScrap,

  createLink, listLink, updateLink, deleteLink
}