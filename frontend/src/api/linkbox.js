import { apiInstance } from './index'

const api = apiInstance();

// 링크박스 관련 코드
function createLinkbox (boxdata, res, err) {
  api.post('linkbox/create', JSON.stringify(boxdata)).then(res).catch(err)
}

function listLinkbox (res, err) {
  api.get('linkbox/list').then(res).catch(err)
}

function listPopLinkbox (res, err) {
  api.get('linkbox/popular').then(res).catch(err)
}

function listInterestLinkbox (userid, res, err) {
  api.get(`linkbox/interests/${userid}`).then(res).catch(err)
}

function listUserLinkbox (userid, res, err) {
  api.get(`linkbox/list/${userid}`).then(res).catch(err)
}

function viewLinkbox (boxid, res, err) {
  api.get(`linkbox/viewcount/${boxid}`).then(res).catch(err)
}

function updateLinkbox (boxdata, res, err) {
  api.put('linkbox', JSON.stringify(boxdata)).then(res).catch(err)
}

function deleteLinkbox (boxid, res, err) {
  api.delete(`linkbox/${boxid}`).then(res).catch(err)
}

// 링크박스 관심사 관련 코드
function addLinkboxInterest (boxdata, res, err) {
  api.post('linkbox/interest', JSON.stringify(boxdata)).then(res).catch(err)
}

function getInterestList (boxid, res, err) {
  api.get(`linkbox/interest/${boxid}`).then(res).catch(err)
}

function deleteLinkboxInterest (boxid, interest, res, err) {
  api.delete(`linkbox/interest?boxid=${boxid}&interest=${interest}`).then(res).catch(err)
}

// 링크박스 정보 불러오기
async function getLinkboxInfo (boxid,  res, err) {
  await api.get(`linkbox/one/${boxid}`).then(res).catch(err)
}

function getLinkboxInterest (boxid,  res, err) {
  api.get(`linkbox/interest/${boxid}`).then(res).catch(err)
}

// 댓글 관련 코드
function createLinkboxComment (boxdata, res, err) {
  api.post('linkbox/comment', JSON.stringify(boxdata)).then(res).catch(err)
}

function deleteLinkboxComment (commentid, res, err) {
  api.delete(`linkbox/comment/${commentid}`).then(res).catch(err)
}

function updateLinkboxComment (boxdata, res, err) {
  api.put('linkbox/comment', JSON.stringify(boxdata)).then(res).catch(err)
}

function searchLinkBoxCommentByBoxId (boxid, res, err) {
  api.get(`linkbox/comment/${boxid}`).then(res).catch(err)
}

// 링크박스 좋아요 관련 코드
function likeLinkbox (userboxdata, res, err) {
  api.post('linkbox/like', JSON.stringify(userboxdata)).then(res).catch(err)
}

function unlikeLinkbox (userboxdata, res, err) {
  api.post('linkbox/like', JSON.stringify(userboxdata)).then(res).catch(err)
}

function getLikeList(boxid, res, err) {
  api.get(`linkbox/like/${boxid}`).then(res).catch(err)
}

function getUserLike(boxid, userid, res, err) {
  api.get(`linkbox/like?boxid=${boxid}&uid=${userid}`).then(res).catch(err)
}

// 링크박스 스크랩 관련 코드
function createScrap (userboxdata, res, err) {
  api.post('linkbox/scrap', JSON.stringify(userboxdata)).then(res).catch(err)
}

function deleteScrap (userid, boxid, res, err) {
  api.delete(`linkbox/scrap?uid=${userid}&boxid=${boxid}`).then(res).catch(err)
}

function getScrapBoxid (boxid, res, err) {
  api.get(`linkbox/scrap/box/${boxid}`).then(res).catch(err)
}

function getScrapUid(userid, res, err) {
  api.get(`linkbox/scrap/user/${userid}`).then(res).catch(err)
}

function getUserScrap(boxid, userid, res, err) {
  api.get(`linkbox/scrap?boxid=${boxid}&uid=${userid}`).then(res).catch(err)
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

  listPopLinkbox, listInterestLinkbox, 

  addLinkboxInterest, deleteLinkboxInterest, getInterestList,
  getLinkboxInfo, getLinkboxInterest,

  createLinkboxComment, deleteLinkboxComment, updateLinkboxComment, searchLinkBoxCommentByBoxId,

  likeLinkbox, unlikeLinkbox, getLikeList, getUserLike,

  createScrap, deleteScrap, getScrapBoxid, getScrapUid, getUserScrap,

  createLink, listLink, updateLink, deleteLink
}