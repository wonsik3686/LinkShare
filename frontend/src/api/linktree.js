import { apiInstance } from './index'

const api = apiInstance();

function createLinktree (linktreedata, res, err) {
  api.post('linktree', JSON.stringify(linktreedata)).then(res).catch(err)
}

function listLinktree (boxid, res, err) {
  api.get(`linktree/${boxid}`).then(res).catch(err)
}

function updateLinktree (linktreedata, res, err) {
  api.post('linktree', JSON.stringify(linktreedata)).then(res).catch(err)
}

function deleteLinktree (treeid, res, err) {
  api.delete(`linktree/${treeid}`,).then(res).catch(err)
}

export { createLinktree, listLinktree, updateLinktree, deleteLinktree }