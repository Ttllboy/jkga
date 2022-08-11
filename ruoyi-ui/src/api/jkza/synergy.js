import request from '@/utils/request'

// 查询整改通知列表
export function listSynergy(query) {
  return request({
    url: '/jkza/synergy/list',
    method: 'get',
    params: query
  })
}

// 查询整改通知详细
export function getSynergy(id) {
  return request({
    url: '/jkza/synergy/' + id,
    method: 'get'
  })
}

// 新增整改通知
export function addSynergy(data) {
  return request({
    url: '/jkza/synergy',
    method: 'post',
    data: data
  })
}

// 修改整改通知
export function updateSynergy(data) {
  return request({
    url: '/jkza/synergy',
    method: 'put',
    data: data
  })
}

// 删除整改通知
export function delSynergy(id) {
  return request({
    url: '/jkza/synergy/' + id,
    method: 'delete'
  })
}

export function listBuildingNames() {
  return request({
    url: '/jkza/building/listBuildingNames',
    method: 'post'
  })
}
