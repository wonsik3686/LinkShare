import { apiInstance } from './index'

const api = apiInstance();

async function createLinktree (linktreedata, res, err) {
  await api.post('linktree', JSON.stringify(linktreedata)).then(res).catch(err)
}

async function listLinktree (boxid, res, err) {
  await api.get(`linktree/${boxid}`).then(res).catch(err)
}

async function updateLinktree (linktreedata, res, err) {
  await api.post('linktree', JSON.stringify(linktreedata)).then(res).catch(err)
}

async function deleteLinktree (treeid, res, err) {
  await api.delete(`linktree/${treeid}`,).then(res).catch(err)
}

export { createLinktree, listLinktree, updateLinktree, deleteLinktree }