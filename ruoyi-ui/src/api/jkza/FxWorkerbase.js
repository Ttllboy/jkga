import request from '@/utils/request'

// 查询工人信息列表
export function listFxWorkerbase(query) {
  return request({
    url: '/jkza/FxWorkerbase/list',
    method: 'get',
    params: query
  })
}

// 查询工人信息详细
export function getFxWorkerbase(personname) {
  return request({
    url: '/jkza/FxWorkerbase/' + personname,
    method: 'get'
  })
}

// 新增工人信息
export function addFxWorkerbase(data) {
  return request({
    url: '/jkza/FxWorkerbase',
    method: 'post',
    data: data
  })
}

// 修改工人信息
export function updateFxWorkerbase(data) {
  return request({
    url: '/jkza/FxWorkerbase',
    method: 'put',
    data: data
  })
}

// 删除工人信息
export function delFxWorkerbase(personname) {
  return request({
    url: '/jkza/FxWorkerbase/' + personname,
    method: 'delete'
  })
}
