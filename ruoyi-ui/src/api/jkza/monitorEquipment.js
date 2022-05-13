import request from '@/utils/request'

// 查询监测设备列表
export function listMonitorEquipment(query) {
  return request({
    url: '/jkza/monitorEquipment/list',
    method: 'get',
    params: query
  })
}

// 查询监测设备详细
export function getMonitorEquipment(id) {
  return request({
    url: '/jkza/monitorEquipment/' + id,
    method: 'get'
  })
}

// 新增监测设备
export function addMonitorEquipment(data) {
  return request({
    url: '/jkza/monitorEquipment',
    method: 'post',
    data: data
  })
}

// 修改监测设备
export function updateMonitorEquipment(data) {
  return request({
    url: '/jkza/monitorEquipment',
    method: 'put',
    data: data
  })
}

// 删除监测设备
export function delMonitorEquipment(id) {
  return request({
    url: '/jkza/monitorEquipment/' + id,
    method: 'delete'
  })
}
